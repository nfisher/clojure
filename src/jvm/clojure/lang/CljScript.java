package clojure.lang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class CljScript {

    public void loadResourceScript(String name, boolean failIfNotFound) throws IOException {
        int slash = name.lastIndexOf('/');
        String file = slash >= 0 ? name.substring(slash + 1) : name;
        InputStream ins = RT.resourceAsStream(RT.baseLoader(), name);
        if (ins != null) {
            try {
                Compiler.load(new InputStreamReader(ins, RT.UTF8), name, file);
            } finally {
                ins.close();
            }
        } else if (failIfNotFound) {
            throw new FileNotFoundException("Could not locate Clojure resource on classpath: " + name);
        }
    }

    public void compile(String cljfile) throws IOException {
        InputStream ins = RT.resourceAsStream(RT.baseLoader(), cljfile);
        if (ins != null) {
            try {
                Compiler.compile(new InputStreamReader(ins, RT.UTF8), cljfile,
                        cljfile.substring(1 + cljfile.lastIndexOf("/")));
            } finally {
                ins.close();
            }

        } else {
            throw new FileNotFoundException("Could not locate Clojure resource on classpath: " + cljfile);
        }
    }

    public void load(String scriptbase, boolean failIfNotFound) throws IOException {
        String classfile = scriptbase + RT.LOADER_SUFFIX + ".class";
        String cljfile = scriptbase + ".clj";
        String scriptfile = cljfile;
        URL classURL = RT.getResource(RT.baseLoader(), classfile);
        URL cljURL = RT.getResource(RT.baseLoader(), scriptfile);
        if (cljURL == null) {
            scriptfile = scriptbase + ".cljc";
            cljURL = RT.getResource(RT.baseLoader(), scriptfile);
        }
        boolean loaded = false;

        if ((classURL != null &&
                (cljURL == null
                        || RT.lastModified(classURL, classfile) > RT.lastModified(cljURL, scriptfile)))
                || classURL == null) {
            try {
                Var.pushThreadBindings(
                        RT.mapUniqueKeys(RT.CURRENT_NS, RT.CURRENT_NS.deref(),
                                RT.WARN_ON_REFLECTION, RT.WARN_ON_REFLECTION.deref()
                                , RT.UNCHECKED_MATH, RT.UNCHECKED_MATH.deref()));
                loaded = (RT.loadClassForName(scriptbase.replace('/', '.') + RT.LOADER_SUFFIX) != null);
            } finally {
                Var.popThreadBindings();
            }
        }
        if (!loaded && cljURL != null) {
            if (RT.BOOLEAN.booleanCast(Compiler.COMPILE_FILES.deref()))
                RT.compile(scriptfile);
            else
                RT.loadResourceScript(RT.class, scriptfile);
        } else if (!loaded && failIfNotFound) {
            throw new FileNotFoundException(String.format("Could not locate %s or %s on classpath.%s", classfile, cljfile,
                    scriptbase.contains("_") ? " Please check that namespaces with dashes use underscores in the Clojure file name." : ""));
        }
    }

    long lastModified(URL url, String libfile) throws IOException {
        URLConnection connection = url.openConnection();
        try {
            if (url.getProtocol().equals("jar"))
                return ((JarURLConnection) connection).getJarFile().getEntry(libfile).getTime();
            else
                return connection.getLastModified();
        } finally {
            InputStream ins = connection.getInputStream();
            if (ins != null)
                ins.close();
        }
    }
}

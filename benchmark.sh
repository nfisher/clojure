#!/bin/sh -eu

rm -f standard.log prefetch.log

time java -cp ../clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")'

a=1
until [ $a -gt 100 ]; do
  echo $a
  { time java -cp ../clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")' > /dev/null ; } 2>> standard.log
  a=`expr $a + 1`
done

time java -cp clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")'

a=1
until [ $a -gt 100 ]; do
  echo $a
  { time java -cp clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")' ; } 2>> prefetch.log
  a=`expr $a + 1`
done

grep ^real standard.log | awk '{ gsub(/(0m|s)/, "", $2); print $2 }' | sort --human-numeric-sort > std.log
grep ^real prefetch.log | awk '{ gsub(/(0m|s)/, "", $2); print $2 }' | sort --human-numeric-sort > pf.log

time java -cp clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")'

a=1
until [ $a -gt 100 ]; do
  echo $a
  { time java -cp clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")' ; } 2>> prefetch2.log
  a=`expr $a + 1`
done

time java -cp ../clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")'

a=1
until [ $a -gt 100 ]; do
  echo $a
  { time java -cp ../clojure.jar:`cat maven-classpath` clojure.main -e '(prn "hello")' > /dev/null ; } 2>> standard2.log
  a=`expr $a + 1`
done

grep ^real standard2.log | awk '{ gsub(/(0m|s)/, "", $2); print $2 }' | sort --human-numeric-sort > std2.log
grep ^real prefetch2.log | awk '{ gsub(/(0m|s)/, "", $2); print $2 }' | sort --human-numeric-sort > pf2.log

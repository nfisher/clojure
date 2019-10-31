/**
 * Copyright (c) Rich Hickey. All rights reserved. The use and distribution terms for this software
 * are covered by the Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
 * which can be found in the file epl-v10.html at the root of this distribution. By using this
 * software in any fashion, you are agreeing to be bound by the terms of this license. You must not
 * remove this notice, or any other, from this software.
 */

/* rich Mar 25, 2006 3:54:03 PM */

package clojure.lang;

import java.util.concurrent.Callable;

/**
 * <code>IFn</code> provides complete access to invoking any of Clojure's <a
 * href="http://clojure.github.io/clojure/">API</a>s. You can also access any other library written
 * in Clojure, after adding either its source or compiled form to the classpath.
 */
public interface IFn extends Callable, Runnable {

  Object invoke();

  Object invoke(Object arg1);

  Object invoke(Object arg1, Object arg2);

  Object invoke(Object arg1, Object arg2, Object arg3);

  Object invoke(Object arg1, Object arg2, Object arg3, Object arg4);

  Object invoke(Object arg1, Object arg2, Object arg3, Object arg4, Object arg5);

  Object invoke(
          Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6);

  Object invoke(
          Object arg1, Object arg2, Object arg3, Object arg4, Object arg5, Object arg6, Object arg7);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15,
          Object arg16);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15,
          Object arg16,
          Object arg17);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15,
          Object arg16,
          Object arg17,
          Object arg18);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15,
          Object arg16,
          Object arg17,
          Object arg18,
          Object arg19);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15,
          Object arg16,
          Object arg17,
          Object arg18,
          Object arg19,
          Object arg20);

  Object invoke(
          Object arg1,
          Object arg2,
          Object arg3,
          Object arg4,
          Object arg5,
          Object arg6,
          Object arg7,
          Object arg8,
          Object arg9,
          Object arg10,
          Object arg11,
          Object arg12,
          Object arg13,
          Object arg14,
          Object arg15,
          Object arg16,
          Object arg17,
          Object arg18,
          Object arg19,
          Object arg20,
          Object... args);

  Object applyTo(ISeq arglist);

  interface L {
    long invokePrim();
  }

  interface D {
    double invokePrim();
  }

  interface OL {
    long invokePrim(Object arg0);
  }

  interface OD {
    double invokePrim(Object arg0);
  }

  interface LO {
    Object invokePrim(long arg0);
  }

  interface LL {
    long invokePrim(long arg0);
  }

  interface LD {
    double invokePrim(long arg0);
  }

  interface DO {
    Object invokePrim(double arg0);
  }

  interface DL {
    long invokePrim(double arg0);
  }

  interface DD {
    double invokePrim(double arg0);
  }

  interface OOL {
    long invokePrim(Object arg0, Object arg1);
  }

  interface OOD {
    double invokePrim(Object arg0, Object arg1);
  }

  interface OLO {
    Object invokePrim(Object arg0, long arg1);
  }

  interface OLL {
    long invokePrim(Object arg0, long arg1);
  }

  interface OLD {
    double invokePrim(Object arg0, long arg1);
  }

  interface ODO {
    Object invokePrim(Object arg0, double arg1);
  }

  interface ODL {
    long invokePrim(Object arg0, double arg1);
  }

  interface ODD {
    double invokePrim(Object arg0, double arg1);
  }

  interface LOO {
    Object invokePrim(long arg0, Object arg1);
  }

  interface LOL {
    long invokePrim(long arg0, Object arg1);
  }

  interface LOD {
    double invokePrim(long arg0, Object arg1);
  }

  interface LLO {
    Object invokePrim(long arg0, long arg1);
  }

  interface LLL {
    long invokePrim(long arg0, long arg1);
  }

  interface LLD {
    double invokePrim(long arg0, long arg1);
  }

  interface LDO {
    Object invokePrim(long arg0, double arg1);
  }

  interface LDL {
    long invokePrim(long arg0, double arg1);
  }

  interface LDD {
    double invokePrim(long arg0, double arg1);
  }

  interface DOO {
    Object invokePrim(double arg0, Object arg1);
  }

  interface DOL {
    long invokePrim(double arg0, Object arg1);
  }

  interface DOD {
    double invokePrim(double arg0, Object arg1);
  }

  interface DLO {
    Object invokePrim(double arg0, long arg1);
  }

  interface DLL {
    long invokePrim(double arg0, long arg1);
  }

  interface DLD {
    double invokePrim(double arg0, long arg1);
  }

  interface DDO {
    Object invokePrim(double arg0, double arg1);
  }

  interface DDL {
    long invokePrim(double arg0, double arg1);
  }

  interface DDD {
    double invokePrim(double arg0, double arg1);
  }

  interface OOOL {
    long invokePrim(Object arg0, Object arg1, Object arg2);
  }

  interface OOOD {
    double invokePrim(Object arg0, Object arg1, Object arg2);
  }

  interface OOLO {
    Object invokePrim(Object arg0, Object arg1, long arg2);
  }

  interface OOLL {
    long invokePrim(Object arg0, Object arg1, long arg2);
  }

  interface OOLD {
    double invokePrim(Object arg0, Object arg1, long arg2);
  }

  interface OODO {
    Object invokePrim(Object arg0, Object arg1, double arg2);
  }

  interface OODL {
    long invokePrim(Object arg0, Object arg1, double arg2);
  }

  interface OODD {
    double invokePrim(Object arg0, Object arg1, double arg2);
  }

  interface OLOO {
    Object invokePrim(Object arg0, long arg1, Object arg2);
  }

  interface OLOL {
    long invokePrim(Object arg0, long arg1, Object arg2);
  }

  interface OLOD {
    double invokePrim(Object arg0, long arg1, Object arg2);
  }

  interface OLLO {
    Object invokePrim(Object arg0, long arg1, long arg2);
  }

  interface OLLL {
    long invokePrim(Object arg0, long arg1, long arg2);
  }

  interface OLLD {
    double invokePrim(Object arg0, long arg1, long arg2);
  }

  interface OLDO {
    Object invokePrim(Object arg0, long arg1, double arg2);
  }

  interface OLDL {
    long invokePrim(Object arg0, long arg1, double arg2);
  }

  interface OLDD {
    double invokePrim(Object arg0, long arg1, double arg2);
  }

  interface ODOO {
    Object invokePrim(Object arg0, double arg1, Object arg2);
  }

  interface ODOL {
    long invokePrim(Object arg0, double arg1, Object arg2);
  }

  interface ODOD {
    double invokePrim(Object arg0, double arg1, Object arg2);
  }

  interface ODLO {
    Object invokePrim(Object arg0, double arg1, long arg2);
  }

  interface ODLL {
    long invokePrim(Object arg0, double arg1, long arg2);
  }

  interface ODLD {
    double invokePrim(Object arg0, double arg1, long arg2);
  }

  interface ODDO {
    Object invokePrim(Object arg0, double arg1, double arg2);
  }

  interface ODDL {
    long invokePrim(Object arg0, double arg1, double arg2);
  }

  interface ODDD {
    double invokePrim(Object arg0, double arg1, double arg2);
  }

  interface LOOO {
    Object invokePrim(long arg0, Object arg1, Object arg2);
  }

  interface LOOL {
    long invokePrim(long arg0, Object arg1, Object arg2);
  }

  interface LOOD {
    double invokePrim(long arg0, Object arg1, Object arg2);
  }

  interface LOLO {
    Object invokePrim(long arg0, Object arg1, long arg2);
  }

  interface LOLL {
    long invokePrim(long arg0, Object arg1, long arg2);
  }

  interface LOLD {
    double invokePrim(long arg0, Object arg1, long arg2);
  }

  interface LODO {
    Object invokePrim(long arg0, Object arg1, double arg2);
  }

  interface LODL {
    long invokePrim(long arg0, Object arg1, double arg2);
  }

  interface LODD {
    double invokePrim(long arg0, Object arg1, double arg2);
  }

  interface LLOO {
    Object invokePrim(long arg0, long arg1, Object arg2);
  }

  interface LLOL {
    long invokePrim(long arg0, long arg1, Object arg2);
  }

  interface LLOD {
    double invokePrim(long arg0, long arg1, Object arg2);
  }

  interface LLLO {
    Object invokePrim(long arg0, long arg1, long arg2);
  }

  interface LLLL {
    long invokePrim(long arg0, long arg1, long arg2);
  }

  interface LLLD {
    double invokePrim(long arg0, long arg1, long arg2);
  }

  interface LLDO {
    Object invokePrim(long arg0, long arg1, double arg2);
  }

  interface LLDL {
    long invokePrim(long arg0, long arg1, double arg2);
  }

  interface LLDD {
    double invokePrim(long arg0, long arg1, double arg2);
  }

  interface LDOO {
    Object invokePrim(long arg0, double arg1, Object arg2);
  }

  interface LDOL {
    long invokePrim(long arg0, double arg1, Object arg2);
  }

  interface LDOD {
    double invokePrim(long arg0, double arg1, Object arg2);
  }

  interface LDLO {
    Object invokePrim(long arg0, double arg1, long arg2);
  }

  interface LDLL {
    long invokePrim(long arg0, double arg1, long arg2);
  }

  interface LDLD {
    double invokePrim(long arg0, double arg1, long arg2);
  }

  interface LDDO {
    Object invokePrim(long arg0, double arg1, double arg2);
  }

  interface LDDL {
    long invokePrim(long arg0, double arg1, double arg2);
  }

  interface LDDD {
    double invokePrim(long arg0, double arg1, double arg2);
  }

  interface DOOO {
    Object invokePrim(double arg0, Object arg1, Object arg2);
  }

  interface DOOL {
    long invokePrim(double arg0, Object arg1, Object arg2);
  }

  interface DOOD {
    double invokePrim(double arg0, Object arg1, Object arg2);
  }

  interface DOLO {
    Object invokePrim(double arg0, Object arg1, long arg2);
  }

  interface DOLL {
    long invokePrim(double arg0, Object arg1, long arg2);
  }

  interface DOLD {
    double invokePrim(double arg0, Object arg1, long arg2);
  }

  interface DODO {
    Object invokePrim(double arg0, Object arg1, double arg2);
  }

  interface DODL {
    long invokePrim(double arg0, Object arg1, double arg2);
  }

  interface DODD {
    double invokePrim(double arg0, Object arg1, double arg2);
  }

  interface DLOO {
    Object invokePrim(double arg0, long arg1, Object arg2);
  }

  interface DLOL {
    long invokePrim(double arg0, long arg1, Object arg2);
  }

  interface DLOD {
    double invokePrim(double arg0, long arg1, Object arg2);
  }

  interface DLLO {
    Object invokePrim(double arg0, long arg1, long arg2);
  }

  interface DLLL {
    long invokePrim(double arg0, long arg1, long arg2);
  }

  interface DLLD {
    double invokePrim(double arg0, long arg1, long arg2);
  }

  interface DLDO {
    Object invokePrim(double arg0, long arg1, double arg2);
  }

  interface DLDL {
    long invokePrim(double arg0, long arg1, double arg2);
  }

  interface DLDD {
    double invokePrim(double arg0, long arg1, double arg2);
  }

  interface DDOO {
    Object invokePrim(double arg0, double arg1, Object arg2);
  }

  interface DDOL {
    long invokePrim(double arg0, double arg1, Object arg2);
  }

  interface DDOD {
    double invokePrim(double arg0, double arg1, Object arg2);
  }

  interface DDLO {
    Object invokePrim(double arg0, double arg1, long arg2);
  }

  interface DDLL {
    long invokePrim(double arg0, double arg1, long arg2);
  }

  interface DDLD {
    double invokePrim(double arg0, double arg1, long arg2);
  }

  interface DDDO {
    Object invokePrim(double arg0, double arg1, double arg2);
  }

  interface DDDL {
    long invokePrim(double arg0, double arg1, double arg2);
  }

  interface DDDD {
    double invokePrim(double arg0, double arg1, double arg2);
  }

  interface OOOOL {
    long invokePrim(Object arg0, Object arg1, Object arg2, Object arg3);
  }

  interface OOOOD {
    double invokePrim(Object arg0, Object arg1, Object arg2, Object arg3);
  }

  interface OOOLO {
    Object invokePrim(Object arg0, Object arg1, Object arg2, long arg3);
  }

  interface OOOLL {
    long invokePrim(Object arg0, Object arg1, Object arg2, long arg3);
  }

  interface OOOLD {
    double invokePrim(Object arg0, Object arg1, Object arg2, long arg3);
  }

  interface OOODO {
    Object invokePrim(Object arg0, Object arg1, Object arg2, double arg3);
  }

  interface OOODL {
    long invokePrim(Object arg0, Object arg1, Object arg2, double arg3);
  }

  interface OOODD {
    double invokePrim(Object arg0, Object arg1, Object arg2, double arg3);
  }

  interface OOLOO {
    Object invokePrim(Object arg0, Object arg1, long arg2, Object arg3);
  }

  interface OOLOL {
    long invokePrim(Object arg0, Object arg1, long arg2, Object arg3);
  }

  interface OOLOD {
    double invokePrim(Object arg0, Object arg1, long arg2, Object arg3);
  }

  interface OOLLO {
    Object invokePrim(Object arg0, Object arg1, long arg2, long arg3);
  }

  interface OOLLL {
    long invokePrim(Object arg0, Object arg1, long arg2, long arg3);
  }

  interface OOLLD {
    double invokePrim(Object arg0, Object arg1, long arg2, long arg3);
  }

  interface OOLDO {
    Object invokePrim(Object arg0, Object arg1, long arg2, double arg3);
  }

  interface OOLDL {
    long invokePrim(Object arg0, Object arg1, long arg2, double arg3);
  }

  interface OOLDD {
    double invokePrim(Object arg0, Object arg1, long arg2, double arg3);
  }

  interface OODOO {
    Object invokePrim(Object arg0, Object arg1, double arg2, Object arg3);
  }

  interface OODOL {
    long invokePrim(Object arg0, Object arg1, double arg2, Object arg3);
  }

  interface OODOD {
    double invokePrim(Object arg0, Object arg1, double arg2, Object arg3);
  }

  interface OODLO {
    Object invokePrim(Object arg0, Object arg1, double arg2, long arg3);
  }

  interface OODLL {
    long invokePrim(Object arg0, Object arg1, double arg2, long arg3);
  }

  interface OODLD {
    double invokePrim(Object arg0, Object arg1, double arg2, long arg3);
  }

  interface OODDO {
    Object invokePrim(Object arg0, Object arg1, double arg2, double arg3);
  }

  interface OODDL {
    long invokePrim(Object arg0, Object arg1, double arg2, double arg3);
  }

  interface OODDD {
    double invokePrim(Object arg0, Object arg1, double arg2, double arg3);
  }

  interface OLOOO {
    Object invokePrim(Object arg0, long arg1, Object arg2, Object arg3);
  }

  interface OLOOL {
    long invokePrim(Object arg0, long arg1, Object arg2, Object arg3);
  }

  interface OLOOD {
    double invokePrim(Object arg0, long arg1, Object arg2, Object arg3);
  }

  interface OLOLO {
    Object invokePrim(Object arg0, long arg1, Object arg2, long arg3);
  }

  interface OLOLL {
    long invokePrim(Object arg0, long arg1, Object arg2, long arg3);
  }

  interface OLOLD {
    double invokePrim(Object arg0, long arg1, Object arg2, long arg3);
  }

  interface OLODO {
    Object invokePrim(Object arg0, long arg1, Object arg2, double arg3);
  }

  interface OLODL {
    long invokePrim(Object arg0, long arg1, Object arg2, double arg3);
  }

  interface OLODD {
    double invokePrim(Object arg0, long arg1, Object arg2, double arg3);
  }

  interface OLLOO {
    Object invokePrim(Object arg0, long arg1, long arg2, Object arg3);
  }

  interface OLLOL {
    long invokePrim(Object arg0, long arg1, long arg2, Object arg3);
  }

  interface OLLOD {
    double invokePrim(Object arg0, long arg1, long arg2, Object arg3);
  }

  interface OLLLO {
    Object invokePrim(Object arg0, long arg1, long arg2, long arg3);
  }

  interface OLLLL {
    long invokePrim(Object arg0, long arg1, long arg2, long arg3);
  }

  interface OLLLD {
    double invokePrim(Object arg0, long arg1, long arg2, long arg3);
  }

  interface OLLDO {
    Object invokePrim(Object arg0, long arg1, long arg2, double arg3);
  }

  interface OLLDL {
    long invokePrim(Object arg0, long arg1, long arg2, double arg3);
  }

  interface OLLDD {
    double invokePrim(Object arg0, long arg1, long arg2, double arg3);
  }

  interface OLDOO {
    Object invokePrim(Object arg0, long arg1, double arg2, Object arg3);
  }

  interface OLDOL {
    long invokePrim(Object arg0, long arg1, double arg2, Object arg3);
  }

  interface OLDOD {
    double invokePrim(Object arg0, long arg1, double arg2, Object arg3);
  }

  interface OLDLO {
    Object invokePrim(Object arg0, long arg1, double arg2, long arg3);
  }

  interface OLDLL {
    long invokePrim(Object arg0, long arg1, double arg2, long arg3);
  }

  interface OLDLD {
    double invokePrim(Object arg0, long arg1, double arg2, long arg3);
  }

  interface OLDDO {
    Object invokePrim(Object arg0, long arg1, double arg2, double arg3);
  }

  interface OLDDL {
    long invokePrim(Object arg0, long arg1, double arg2, double arg3);
  }

  interface OLDDD {
    double invokePrim(Object arg0, long arg1, double arg2, double arg3);
  }

  interface ODOOO {
    Object invokePrim(Object arg0, double arg1, Object arg2, Object arg3);
  }

  interface ODOOL {
    long invokePrim(Object arg0, double arg1, Object arg2, Object arg3);
  }

  interface ODOOD {
    double invokePrim(Object arg0, double arg1, Object arg2, Object arg3);
  }

  interface ODOLO {
    Object invokePrim(Object arg0, double arg1, Object arg2, long arg3);
  }

  interface ODOLL {
    long invokePrim(Object arg0, double arg1, Object arg2, long arg3);
  }

  interface ODOLD {
    double invokePrim(Object arg0, double arg1, Object arg2, long arg3);
  }

  interface ODODO {
    Object invokePrim(Object arg0, double arg1, Object arg2, double arg3);
  }

  interface ODODL {
    long invokePrim(Object arg0, double arg1, Object arg2, double arg3);
  }

  interface ODODD {
    double invokePrim(Object arg0, double arg1, Object arg2, double arg3);
  }

  interface ODLOO {
    Object invokePrim(Object arg0, double arg1, long arg2, Object arg3);
  }

  interface ODLOL {
    long invokePrim(Object arg0, double arg1, long arg2, Object arg3);
  }

  interface ODLOD {
    double invokePrim(Object arg0, double arg1, long arg2, Object arg3);
  }

  interface ODLLO {
    Object invokePrim(Object arg0, double arg1, long arg2, long arg3);
  }

  interface ODLLL {
    long invokePrim(Object arg0, double arg1, long arg2, long arg3);
  }

  interface ODLLD {
    double invokePrim(Object arg0, double arg1, long arg2, long arg3);
  }

  interface ODLDO {
    Object invokePrim(Object arg0, double arg1, long arg2, double arg3);
  }

  interface ODLDL {
    long invokePrim(Object arg0, double arg1, long arg2, double arg3);
  }

  interface ODLDD {
    double invokePrim(Object arg0, double arg1, long arg2, double arg3);
  }

  interface ODDOO {
    Object invokePrim(Object arg0, double arg1, double arg2, Object arg3);
  }

  interface ODDOL {
    long invokePrim(Object arg0, double arg1, double arg2, Object arg3);
  }

  interface ODDOD {
    double invokePrim(Object arg0, double arg1, double arg2, Object arg3);
  }

  interface ODDLO {
    Object invokePrim(Object arg0, double arg1, double arg2, long arg3);
  }

  interface ODDLL {
    long invokePrim(Object arg0, double arg1, double arg2, long arg3);
  }

  interface ODDLD {
    double invokePrim(Object arg0, double arg1, double arg2, long arg3);
  }

  interface ODDDO {
    Object invokePrim(Object arg0, double arg1, double arg2, double arg3);
  }

  interface ODDDL {
    long invokePrim(Object arg0, double arg1, double arg2, double arg3);
  }

  interface ODDDD {
    double invokePrim(Object arg0, double arg1, double arg2, double arg3);
  }

  interface LOOOO {
    Object invokePrim(long arg0, Object arg1, Object arg2, Object arg3);
  }

  interface LOOOL {
    long invokePrim(long arg0, Object arg1, Object arg2, Object arg3);
  }

  interface LOOOD {
    double invokePrim(long arg0, Object arg1, Object arg2, Object arg3);
  }

  interface LOOLO {
    Object invokePrim(long arg0, Object arg1, Object arg2, long arg3);
  }

  interface LOOLL {
    long invokePrim(long arg0, Object arg1, Object arg2, long arg3);
  }

  interface LOOLD {
    double invokePrim(long arg0, Object arg1, Object arg2, long arg3);
  }

  interface LOODO {
    Object invokePrim(long arg0, Object arg1, Object arg2, double arg3);
  }

  interface LOODL {
    long invokePrim(long arg0, Object arg1, Object arg2, double arg3);
  }

  interface LOODD {
    double invokePrim(long arg0, Object arg1, Object arg2, double arg3);
  }

  interface LOLOO {
    Object invokePrim(long arg0, Object arg1, long arg2, Object arg3);
  }

  interface LOLOL {
    long invokePrim(long arg0, Object arg1, long arg2, Object arg3);
  }

  interface LOLOD {
    double invokePrim(long arg0, Object arg1, long arg2, Object arg3);
  }

  interface LOLLO {
    Object invokePrim(long arg0, Object arg1, long arg2, long arg3);
  }

  interface LOLLL {
    long invokePrim(long arg0, Object arg1, long arg2, long arg3);
  }

  interface LOLLD {
    double invokePrim(long arg0, Object arg1, long arg2, long arg3);
  }

  interface LOLDO {
    Object invokePrim(long arg0, Object arg1, long arg2, double arg3);
  }

  interface LOLDL {
    long invokePrim(long arg0, Object arg1, long arg2, double arg3);
  }

  interface LOLDD {
    double invokePrim(long arg0, Object arg1, long arg2, double arg3);
  }

  interface LODOO {
    Object invokePrim(long arg0, Object arg1, double arg2, Object arg3);
  }

  interface LODOL {
    long invokePrim(long arg0, Object arg1, double arg2, Object arg3);
  }

  interface LODOD {
    double invokePrim(long arg0, Object arg1, double arg2, Object arg3);
  }

  interface LODLO {
    Object invokePrim(long arg0, Object arg1, double arg2, long arg3);
  }

  interface LODLL {
    long invokePrim(long arg0, Object arg1, double arg2, long arg3);
  }

  interface LODLD {
    double invokePrim(long arg0, Object arg1, double arg2, long arg3);
  }

  interface LODDO {
    Object invokePrim(long arg0, Object arg1, double arg2, double arg3);
  }

  interface LODDL {
    long invokePrim(long arg0, Object arg1, double arg2, double arg3);
  }

  interface LODDD {
    double invokePrim(long arg0, Object arg1, double arg2, double arg3);
  }

  interface LLOOO {
    Object invokePrim(long arg0, long arg1, Object arg2, Object arg3);
  }

  interface LLOOL {
    long invokePrim(long arg0, long arg1, Object arg2, Object arg3);
  }

  interface LLOOD {
    double invokePrim(long arg0, long arg1, Object arg2, Object arg3);
  }

  interface LLOLO {
    Object invokePrim(long arg0, long arg1, Object arg2, long arg3);
  }

  interface LLOLL {
    long invokePrim(long arg0, long arg1, Object arg2, long arg3);
  }

  interface LLOLD {
    double invokePrim(long arg0, long arg1, Object arg2, long arg3);
  }

  interface LLODO {
    Object invokePrim(long arg0, long arg1, Object arg2, double arg3);
  }

  interface LLODL {
    long invokePrim(long arg0, long arg1, Object arg2, double arg3);
  }

  interface LLODD {
    double invokePrim(long arg0, long arg1, Object arg2, double arg3);
  }

  interface LLLOO {
    Object invokePrim(long arg0, long arg1, long arg2, Object arg3);
  }

  interface LLLOL {
    long invokePrim(long arg0, long arg1, long arg2, Object arg3);
  }

  interface LLLOD {
    double invokePrim(long arg0, long arg1, long arg2, Object arg3);
  }

  interface LLLLO {
    Object invokePrim(long arg0, long arg1, long arg2, long arg3);
  }

  interface LLLLL {
    long invokePrim(long arg0, long arg1, long arg2, long arg3);
  }

  interface LLLLD {
    double invokePrim(long arg0, long arg1, long arg2, long arg3);
  }

  interface LLLDO {
    Object invokePrim(long arg0, long arg1, long arg2, double arg3);
  }

  interface LLLDL {
    long invokePrim(long arg0, long arg1, long arg2, double arg3);
  }

  interface LLLDD {
    double invokePrim(long arg0, long arg1, long arg2, double arg3);
  }

  interface LLDOO {
    Object invokePrim(long arg0, long arg1, double arg2, Object arg3);
  }

  interface LLDOL {
    long invokePrim(long arg0, long arg1, double arg2, Object arg3);
  }

  interface LLDOD {
    double invokePrim(long arg0, long arg1, double arg2, Object arg3);
  }

  interface LLDLO {
    Object invokePrim(long arg0, long arg1, double arg2, long arg3);
  }

  interface LLDLL {
    long invokePrim(long arg0, long arg1, double arg2, long arg3);
  }

  interface LLDLD {
    double invokePrim(long arg0, long arg1, double arg2, long arg3);
  }

  interface LLDDO {
    Object invokePrim(long arg0, long arg1, double arg2, double arg3);
  }

  interface LLDDL {
    long invokePrim(long arg0, long arg1, double arg2, double arg3);
  }

  interface LLDDD {
    double invokePrim(long arg0, long arg1, double arg2, double arg3);
  }

  interface LDOOO {
    Object invokePrim(long arg0, double arg1, Object arg2, Object arg3);
  }

  interface LDOOL {
    long invokePrim(long arg0, double arg1, Object arg2, Object arg3);
  }

  interface LDOOD {
    double invokePrim(long arg0, double arg1, Object arg2, Object arg3);
  }

  interface LDOLO {
    Object invokePrim(long arg0, double arg1, Object arg2, long arg3);
  }

  interface LDOLL {
    long invokePrim(long arg0, double arg1, Object arg2, long arg3);
  }

  interface LDOLD {
    double invokePrim(long arg0, double arg1, Object arg2, long arg3);
  }

  interface LDODO {
    Object invokePrim(long arg0, double arg1, Object arg2, double arg3);
  }

  interface LDODL {
    long invokePrim(long arg0, double arg1, Object arg2, double arg3);
  }

  interface LDODD {
    double invokePrim(long arg0, double arg1, Object arg2, double arg3);
  }

  interface LDLOO {
    Object invokePrim(long arg0, double arg1, long arg2, Object arg3);
  }

  interface LDLOL {
    long invokePrim(long arg0, double arg1, long arg2, Object arg3);
  }

  interface LDLOD {
    double invokePrim(long arg0, double arg1, long arg2, Object arg3);
  }

  interface LDLLO {
    Object invokePrim(long arg0, double arg1, long arg2, long arg3);
  }

  interface LDLLL {
    long invokePrim(long arg0, double arg1, long arg2, long arg3);
  }

  interface LDLLD {
    double invokePrim(long arg0, double arg1, long arg2, long arg3);
  }

  interface LDLDO {
    Object invokePrim(long arg0, double arg1, long arg2, double arg3);
  }

  interface LDLDL {
    long invokePrim(long arg0, double arg1, long arg2, double arg3);
  }

  interface LDLDD {
    double invokePrim(long arg0, double arg1, long arg2, double arg3);
  }

  interface LDDOO {
    Object invokePrim(long arg0, double arg1, double arg2, Object arg3);
  }

  interface LDDOL {
    long invokePrim(long arg0, double arg1, double arg2, Object arg3);
  }

  interface LDDOD {
    double invokePrim(long arg0, double arg1, double arg2, Object arg3);
  }

  interface LDDLO {
    Object invokePrim(long arg0, double arg1, double arg2, long arg3);
  }

  interface LDDLL {
    long invokePrim(long arg0, double arg1, double arg2, long arg3);
  }

  interface LDDLD {
    double invokePrim(long arg0, double arg1, double arg2, long arg3);
  }

  interface LDDDO {
    Object invokePrim(long arg0, double arg1, double arg2, double arg3);
  }

  interface LDDDL {
    long invokePrim(long arg0, double arg1, double arg2, double arg3);
  }

  interface LDDDD {
    double invokePrim(long arg0, double arg1, double arg2, double arg3);
  }

  interface DOOOO {
    Object invokePrim(double arg0, Object arg1, Object arg2, Object arg3);
  }

  interface DOOOL {
    long invokePrim(double arg0, Object arg1, Object arg2, Object arg3);
  }

  interface DOOOD {
    double invokePrim(double arg0, Object arg1, Object arg2, Object arg3);
  }

  interface DOOLO {
    Object invokePrim(double arg0, Object arg1, Object arg2, long arg3);
  }

  interface DOOLL {
    long invokePrim(double arg0, Object arg1, Object arg2, long arg3);
  }

  interface DOOLD {
    double invokePrim(double arg0, Object arg1, Object arg2, long arg3);
  }

  interface DOODO {
    Object invokePrim(double arg0, Object arg1, Object arg2, double arg3);
  }

  interface DOODL {
    long invokePrim(double arg0, Object arg1, Object arg2, double arg3);
  }

  interface DOODD {
    double invokePrim(double arg0, Object arg1, Object arg2, double arg3);
  }

  interface DOLOO {
    Object invokePrim(double arg0, Object arg1, long arg2, Object arg3);
  }

  interface DOLOL {
    long invokePrim(double arg0, Object arg1, long arg2, Object arg3);
  }

  interface DOLOD {
    double invokePrim(double arg0, Object arg1, long arg2, Object arg3);
  }

  interface DOLLO {
    Object invokePrim(double arg0, Object arg1, long arg2, long arg3);
  }

  interface DOLLL {
    long invokePrim(double arg0, Object arg1, long arg2, long arg3);
  }

  interface DOLLD {
    double invokePrim(double arg0, Object arg1, long arg2, long arg3);
  }

  interface DOLDO {
    Object invokePrim(double arg0, Object arg1, long arg2, double arg3);
  }

  interface DOLDL {
    long invokePrim(double arg0, Object arg1, long arg2, double arg3);
  }

  interface DOLDD {
    double invokePrim(double arg0, Object arg1, long arg2, double arg3);
  }

  interface DODOO {
    Object invokePrim(double arg0, Object arg1, double arg2, Object arg3);
  }

  interface DODOL {
    long invokePrim(double arg0, Object arg1, double arg2, Object arg3);
  }

  interface DODOD {
    double invokePrim(double arg0, Object arg1, double arg2, Object arg3);
  }

  interface DODLO {
    Object invokePrim(double arg0, Object arg1, double arg2, long arg3);
  }

  interface DODLL {
    long invokePrim(double arg0, Object arg1, double arg2, long arg3);
  }

  interface DODLD {
    double invokePrim(double arg0, Object arg1, double arg2, long arg3);
  }

  interface DODDO {
    Object invokePrim(double arg0, Object arg1, double arg2, double arg3);
  }

  interface DODDL {
    long invokePrim(double arg0, Object arg1, double arg2, double arg3);
  }

  interface DODDD {
    double invokePrim(double arg0, Object arg1, double arg2, double arg3);
  }

  interface DLOOO {
    Object invokePrim(double arg0, long arg1, Object arg2, Object arg3);
  }

  interface DLOOL {
    long invokePrim(double arg0, long arg1, Object arg2, Object arg3);
  }

  interface DLOOD {
    double invokePrim(double arg0, long arg1, Object arg2, Object arg3);
  }

  interface DLOLO {
    Object invokePrim(double arg0, long arg1, Object arg2, long arg3);
  }

  interface DLOLL {
    long invokePrim(double arg0, long arg1, Object arg2, long arg3);
  }

  interface DLOLD {
    double invokePrim(double arg0, long arg1, Object arg2, long arg3);
  }

  interface DLODO {
    Object invokePrim(double arg0, long arg1, Object arg2, double arg3);
  }

  interface DLODL {
    long invokePrim(double arg0, long arg1, Object arg2, double arg3);
  }

  interface DLODD {
    double invokePrim(double arg0, long arg1, Object arg2, double arg3);
  }

  interface DLLOO {
    Object invokePrim(double arg0, long arg1, long arg2, Object arg3);
  }

  interface DLLOL {
    long invokePrim(double arg0, long arg1, long arg2, Object arg3);
  }

  interface DLLOD {
    double invokePrim(double arg0, long arg1, long arg2, Object arg3);
  }

  interface DLLLO {
    Object invokePrim(double arg0, long arg1, long arg2, long arg3);
  }

  interface DLLLL {
    long invokePrim(double arg0, long arg1, long arg2, long arg3);
  }

  interface DLLLD {
    double invokePrim(double arg0, long arg1, long arg2, long arg3);
  }

  interface DLLDO {
    Object invokePrim(double arg0, long arg1, long arg2, double arg3);
  }

  interface DLLDL {
    long invokePrim(double arg0, long arg1, long arg2, double arg3);
  }

  interface DLLDD {
    double invokePrim(double arg0, long arg1, long arg2, double arg3);
  }

  interface DLDOO {
    Object invokePrim(double arg0, long arg1, double arg2, Object arg3);
  }

  interface DLDOL {
    long invokePrim(double arg0, long arg1, double arg2, Object arg3);
  }

  interface DLDOD {
    double invokePrim(double arg0, long arg1, double arg2, Object arg3);
  }

  interface DLDLO {
    Object invokePrim(double arg0, long arg1, double arg2, long arg3);
  }

  interface DLDLL {
    long invokePrim(double arg0, long arg1, double arg2, long arg3);
  }

  interface DLDLD {
    double invokePrim(double arg0, long arg1, double arg2, long arg3);
  }

  interface DLDDO {
    Object invokePrim(double arg0, long arg1, double arg2, double arg3);
  }

  interface DLDDL {
    long invokePrim(double arg0, long arg1, double arg2, double arg3);
  }

  interface DLDDD {
    double invokePrim(double arg0, long arg1, double arg2, double arg3);
  }

  interface DDOOO {
    Object invokePrim(double arg0, double arg1, Object arg2, Object arg3);
  }

  interface DDOOL {
    long invokePrim(double arg0, double arg1, Object arg2, Object arg3);
  }

  interface DDOOD {
    double invokePrim(double arg0, double arg1, Object arg2, Object arg3);
  }

  interface DDOLO {
    Object invokePrim(double arg0, double arg1, Object arg2, long arg3);
  }

  interface DDOLL {
    long invokePrim(double arg0, double arg1, Object arg2, long arg3);
  }

  interface DDOLD {
    double invokePrim(double arg0, double arg1, Object arg2, long arg3);
  }

  interface DDODO {
    Object invokePrim(double arg0, double arg1, Object arg2, double arg3);
  }

  interface DDODL {
    long invokePrim(double arg0, double arg1, Object arg2, double arg3);
  }

  interface DDODD {
    double invokePrim(double arg0, double arg1, Object arg2, double arg3);
  }

  interface DDLOO {
    Object invokePrim(double arg0, double arg1, long arg2, Object arg3);
  }

  interface DDLOL {
    long invokePrim(double arg0, double arg1, long arg2, Object arg3);
  }

  interface DDLOD {
    double invokePrim(double arg0, double arg1, long arg2, Object arg3);
  }

  interface DDLLO {
    Object invokePrim(double arg0, double arg1, long arg2, long arg3);
  }

  interface DDLLL {
    long invokePrim(double arg0, double arg1, long arg2, long arg3);
  }

  interface DDLLD {
    double invokePrim(double arg0, double arg1, long arg2, long arg3);
  }

  interface DDLDO {
    Object invokePrim(double arg0, double arg1, long arg2, double arg3);
  }

  interface DDLDL {
    long invokePrim(double arg0, double arg1, long arg2, double arg3);
  }

  interface DDLDD {
    double invokePrim(double arg0, double arg1, long arg2, double arg3);
  }

  interface DDDOO {
    Object invokePrim(double arg0, double arg1, double arg2, Object arg3);
  }

  interface DDDOL {
    long invokePrim(double arg0, double arg1, double arg2, Object arg3);
  }

  interface DDDOD {
    double invokePrim(double arg0, double arg1, double arg2, Object arg3);
  }

  interface DDDLO {
    Object invokePrim(double arg0, double arg1, double arg2, long arg3);
  }

  interface DDDLL {
    long invokePrim(double arg0, double arg1, double arg2, long arg3);
  }

  interface DDDLD {
    double invokePrim(double arg0, double arg1, double arg2, long arg3);
  }

  interface DDDDO {
    Object invokePrim(double arg0, double arg1, double arg2, double arg3);
  }

  interface DDDDL {
    long invokePrim(double arg0, double arg1, double arg2, double arg3);
  }

  interface DDDDD {
    double invokePrim(double arg0, double arg1, double arg2, double arg3);
  }
}

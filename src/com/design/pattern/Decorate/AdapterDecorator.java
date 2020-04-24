package com.design.pattern.Decorate;

/*
 *now it's also possible to build something called an adapter decorator so centrally a merger of the adapter and decorator
 * pattern so the decorator provides additional functionality while the adapter tries to mimic some interfaces or
 * make some things a bit easier to use.
 */

import java.util.stream.IntStream;

class MyStringBuilder{

    //we will aggregate it and provide all the API
    private StringBuilder strb;

    public MyStringBuilder() {
        strb = new StringBuilder();
    }

    public MyStringBuilder(String str) {
        this.strb = new StringBuilder(str);
    }

    /*
    there is much bigger problem with the code generation
    - we want to MyStringBuilder and if we want to make the change we have to go through all of these cases to make the
    change and do 'return this;'

     */

    //adapter part
    public MyStringBuilder concat(String str){
        return new MyStringBuilder(strb.toString().concat(str));
    }

    public MyStringBuilder appendLine(String str){
        strb.append(str).append(System.lineSeparator());
        return this;
    }

    @Override
    public String toString() {
       return strb.toString();
    }

    public int compareTo(StringBuilder another) {
        return strb.compareTo(another);
    }

    //decorator
    public StringBuilder append(Object obj) {
        return strb.append(obj);
    }

    
    public MyStringBuilder append(String str) {
        strb.append(str);
        return this;
    }

    public StringBuilder append(StringBuffer sb) {
        return strb.append(sb);
    }

    public StringBuilder append(CharSequence s) {
        return strb.append(s);
    }

    public StringBuilder append(CharSequence s, int start, int end) {
        return strb.append(s, start, end);
    }

    public StringBuilder append(char[] str) {
        return strb.append(str);
    }

    public StringBuilder append(char[] str, int offset, int len) {
        return strb.append(str, offset, len);
    }

    public StringBuilder append(boolean b) {
        return strb.append(b);
    }

    
    public StringBuilder append(char c) {
        return strb.append(c);
    }

    
    public StringBuilder append(int i) {
        return strb.append(i);
    }

    public StringBuilder append(long lng) {
        return strb.append(lng);
    }

    public StringBuilder append(float f) {
        return strb.append(f);
    }

    public StringBuilder append(double d) {
        return strb.append(d);
    }

    public StringBuilder appendCodePoint(int codePoint) {
        return strb.appendCodePoint(codePoint);
    }

    public StringBuilder delete(int start, int end) {
        return strb.delete(start, end);
    }

    public StringBuilder deleteCharAt(int index) {
        return strb.deleteCharAt(index);
    }

    public StringBuilder replace(int start, int end, String str) {
        return strb.replace(start, end, str);
    }

    public StringBuilder insert(int index, char[] str, int offset, int len) {
        return strb.insert(index, str, offset, len);
    }

    public StringBuilder insert(int offset, Object obj) {
        return strb.insert(offset, obj);
    }

    public StringBuilder insert(int offset, String str) {
        return strb.insert(offset, str);
    }

    public StringBuilder insert(int offset, char[] str) {
        return strb.insert(offset, str);
    }

    public StringBuilder insert(int dstOffset, CharSequence s) {
        return strb.insert(dstOffset, s);
    }

    public StringBuilder insert(int dstOffset, CharSequence s, int start, int end) {
        return strb.insert(dstOffset, s, start, end);
    }

    public StringBuilder insert(int offset, boolean b) {
        return strb.insert(offset, b);
    }

    public StringBuilder insert(int offset, char c) {
        return strb.insert(offset, c);
    }

    public StringBuilder insert(int offset, int i) {
        return strb.insert(offset, i);
    }

    public StringBuilder insert(int offset, long l) {
        return strb.insert(offset, l);
    }

    public StringBuilder insert(int offset, float f) {
        return strb.insert(offset, f);
    }

    public StringBuilder insert(int offset, double d) {
        return strb.insert(offset, d);
    }

    public int indexOf(String str) {
        return strb.indexOf(str);
    }

    public int indexOf(String str, int fromIndex) {
        return strb.indexOf(str, fromIndex);
    }

    public int lastIndexOf(String str) {
        return strb.lastIndexOf(str);
    }

    public int lastIndexOf(String str, int fromIndex) {
        return strb.lastIndexOf(str, fromIndex);
    }

    public StringBuilder reverse() {
        return strb.reverse();
    }

    public int length() {
        return strb.length();
    }

    public int capacity() {
        return strb.capacity();
    }

    public void ensureCapacity(int minimumCapacity) {
        strb.ensureCapacity(minimumCapacity);
    }

    public void trimToSize() {
        strb.trimToSize();
    }

    public void setLength(int newLength) {
        strb.setLength(newLength);
    }

    public char charAt(int index) {
        return strb.charAt(index);
    }

    public int codePointAt(int index) {
        return strb.codePointAt(index);
    }

    public int codePointBefore(int index) {
        return strb.codePointBefore(index);
    }

    public int codePointCount(int beginIndex, int endIndex) {
        return strb.codePointCount(beginIndex, endIndex);
    }

    public int offsetByCodePoints(int index, int codePointOffset) {
        return strb.offsetByCodePoints(index, codePointOffset);
    }

    public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
        strb.getChars(srcBegin, srcEnd, dst, dstBegin);
    }

    public void setCharAt(int index, char ch) {
        strb.setCharAt(index, ch);
    }

    public String substring(int start) {
        return strb.substring(start);
    }

    public CharSequence subSequence(int start, int end) {
        return strb.subSequence(start, end);
    }

    public String substring(int start, int end) {
        return strb.substring(start, end);
    }

    public IntStream chars() {
        return strb.chars();
    }

    public IntStream codePoints() {
        return strb.codePoints();
    }

    public static int compare(CharSequence cs1, CharSequence cs2) {
        return CharSequence.compare(cs1, cs2);
    }
}

public class AdapterDecorator {

    public static void main(String[] args) {

        MyStringBuilder msb = new MyStringBuilder();
        msb.append("hello").appendLine(" world");
        System.out.println(msb.concat(" in addition to that"));
    }
}

package com.ramesh.springboot.myfirstwebapp.test;

public class Test {

    public static void main(String[] args) {
        try {
            int val = 10 / 0;
        } catch (ArithmeticException ae) {
            System.out.print("Block A ");
            return;
        } catch (Exception e) {
            System.out.print("Block E ");
        } finally {
            System.out.print("Block F ");
        }
    }
}

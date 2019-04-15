package com.mmall.concurrency.example.syncContainer;

import com.mmall.concurrency.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;

public class VectorExample3 {

    private static void test1(Vector<Integer> v1){
        //for-each
        for(Integer i:v1){
            if(i.equals(3)){
                v1.remove(v1.indexOf(i));
            }
        }
    }

    private static void test2(Vector<Integer> v1){
        //iterator
        Iterator<Integer> iterator = v1.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    private static void test3(Vector<Integer> v1){
        //for
        for(int i = 0;i<v1.size();i++){
            if(v1.get(i).equals(3)){
                v1.remove(i);
            }
        }
    }


    public static void main(String[] args){

        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);

        //java.util.ConcurrentModificationException
//        test1(vector);

//        java.util.ConcurrentModificationException
//        test2(vector);

        //success
        test3(vector);

    }

}

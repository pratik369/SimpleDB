package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static HashMap<Integer, HashMap<String,String>> db = new HashMap<Integer, HashMap<String,String>>();
    private static int transaction = 0;
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the Database Query");
        while(true) {
            String query = myScanner.nextLine();
            System.out.println(query);
            String []arguments = query.split(" ");

            if(arguments[0].equalsIgnoreCase("end"))
                end();
            if(arguments[0].equalsIgnoreCase("set"))
                set(arguments[1],arguments[2]);
            if(arguments[0].equalsIgnoreCase("get"))
                get(arguments[1]);
            if(arguments[0].equalsIgnoreCase("commit"))
                commit();
            if(arguments[0].equalsIgnoreCase("rollback"))
                rollback();
            if(arguments[0].equalsIgnoreCase("unset"))
                unset(arguments[1]);
            if(arguments[0].equalsIgnoreCase("numequalto"))
                numEqualto(arguments[1]);
        }
    }




    private static void get(String var){
        System.out.println((transaction));
        if(db.containsKey(transaction)){
            HashMap<String,String> map = db.get(transaction);
            if(map.containsKey(var)){
                String value = map.get(var);
                System.out.println(value);
            }
            else
                System.out.println("Null");
        }
        else
            System.out.println("Null");

    }

    private static void set(String var, String value){
        System.out.println("Transaction ID =" + transaction);
        if(db.containsKey(transaction)){
            HashMap<String,String> map3 = new HashMap<String, String>();
            map3 = db.get(transaction);
            if(map3.containsKey(var)){
                map3.put(var,value);
                db.put(transaction,map3);
            }else {
                map3.put(var, value);
                db.put(transaction, map3);
            }

            for(Map.Entry<String,String> entry : map3.entrySet()){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }
        else {
            HashMap<String,String> map1= new HashMap<String, String>();
            map1.put(var,value);
            db.put(transaction,map1);

        }

    }

    private static void rollback(){
        if(db.isEmpty())
            System.out.println("Nothing there to RollBack");
        else {
            System.out.println("db size = "+db.size());
            System.out.println("Transaction removed");
            db.remove(transaction);
            System.out.println(transaction);
            transaction--;
            System.out.println("db size = "+db.size());
        }

    }

    private static void commit(){
        if(db.isEmpty())
            System.out.println("Nothing to Commit");
        else{
            HashMap<String,String> map,newmap = new HashMap<String, String>();
            map = db.get(transaction);
            for(Map.Entry<String,String> entry : map.entrySet()){
               newmap.put(entry.getKey(),entry.getValue());
            }
            transaction++;
            db.put(transaction,newmap);
        }



    }

    private static void unset(String var){
        if(db.isEmpty())
            System.out.println("Null");
        else{
            HashMap<String,String> unset = db.get(transaction);
            if(unset.containsKey(var)){
            unset.remove(var);
            }
            else
                System.out.println("Null");
        }

    }

    private static void numEqualto(String value){
        if(db.isEmpty())
            System.out.println("0");
        else{
            HashMap<String,String> numequal = new HashMap<String, String>();
            numequal = db.get(transaction);
            int count = 0;
            for(Map.Entry<String,String> entry : numequal.entrySet()){
                if(value.equalsIgnoreCase(entry.getValue()))
                    count++;
            }
            if(count>0)
                System.out.println(count);
            else
                System.out.println(count);
        }

    }
    private static void end(){
        System.exit(0);
    }

}

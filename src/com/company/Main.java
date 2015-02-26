package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static HashMap<Integer, HashMap<String,String>> db = new HashMap<Integer, HashMap<String,String>>();
    private static int transaction = 0;
    private static boolean flag = true;
    private static boolean change = false;
    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the Database Query");
        while(true) {
            String query = myScanner.nextLine();
            //System.out.println(query);
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
            if(arguments[0].equalsIgnoreCase("begin"))
                begin();
        }
    }


    // Function used to get the value of the variable..performed in O(1) time..

    private static void get(String var){

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


    //function used to set value of the variable...performed in O(1) time.

    private static void set(String var, String value){

        if(transaction == -1)
            transaction = 0;

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
            change = true;
        }
        else {
            HashMap<String,String> map1= new HashMap<String, String>();
            map1.put(var,value);
            db.put(transaction,map1);

        }

    }

    //Rollback function

    private static void rollback(){
        if(db.isEmpty() || change == false)
            System.out.println("No Transaction");
        else {
            db.remove(transaction);
            transaction--;
        }

    }

    //Transaction beginning

    private static void begin(){
        if(flag){
            flag=false;
            return;
        }else
            commit();

    }

    //Commit
    private static void commit(){
        if(db.isEmpty())
            System.out.println("No Transaction");
        else{
            HashMap<String,String> map,newmap = new HashMap<String, String>();
            map = db.get(transaction);
            for(Map.Entry<String,String> entry : map.entrySet()){
               newmap.put(entry.getKey(),entry.getValue());
            }
            transaction++;
            db.put(transaction,newmap);
            change = false;
        }



    }

    //Unset the value of the variable..performed in O(1) time..

    private static void unset(String var){
        if(db.isEmpty())
            System.out.println("Null");
        else{
            HashMap<String,String> unset = db.get(transaction);
            if(unset.containsKey(var)){
                unset.remove(var);
                change = true;
            }
            else
                System.out.println("Null");
        }

    }

    //numequalto function..performed in O(n) time..

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

    //end function
    private static void end(){
        System.exit(0);
    }

}

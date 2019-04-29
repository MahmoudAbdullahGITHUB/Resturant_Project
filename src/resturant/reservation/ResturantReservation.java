/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resturant.reservation;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



class customers extends Thread{
    private String myname;
    private Resturant re;
  
    customers(String name,Resturant re){
        this.myname=name;
        this.re=re;
    }
    @Override
    public void run(){
      //  System.out.println(this.myname+" just arrived  ");
          System.out.println(Thread.currentThread().getName()+" just arrived  ");
       
          try {
         //   Thread.sleep(500);
            re.Wait();
         //   Thread.sleep(500);
            re.setdown();
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(customers.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        re.leave();
        
    }
}

class Resturant{
    private int numTable;
    private int numcustomers;
    private int busy=0;

    public Resturant(int n,int tc) {
    
        this.numTable=n;
        this.numcustomers=tc;
    }
    
    
    public void Wait() throws InterruptedException {
      
        
        while(true){
            if(this.busy<this.numTable){
                ++busy; 
                System.out.println(Thread.currentThread().getName()+"\t HERE YOU ARE ,sir : ther is avilable table here");
                break;
            }
            System.out.println(Thread.currentThread().getName()+" is wating //////////////////////////////");
            Thread.sleep(1000);
        }       
    }
   
    public synchronized void setdown() throws InterruptedException {  
        System.out.println(Thread.currentThread().getName()+" \t sat down ");
        System.out.println(Thread.currentThread().getName()+" \t ordered food ");
        System.out.println(Thread.currentThread().getName()+" \t eat ");
       // Thread.sleep(500);
    }
    
    public synchronized void leave() {
        System.out.println(Thread.currentThread().getName()+" \t leaved ");
        --busy;
    }

}

public class ResturantReservation {

    
    public static void main(String[] args) {

        
        int n , tc;
       
       
        System.out.println("enter the number of tables in the resturant ");
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        System.out.println("enter the number customers ");
        tc=sc.nextInt();
      
        Resturant re=new Resturant(n,tc);
      
        for(int i=0;i<tc;i++){
     
            customers c=new customers("c"+i, re);
            c.start();
            
        }
            /* customers c1=new customers("co1",re);
        customers c2=new customers("co2",re);
        c1.start();
        c2.start();
    */
    }    
}

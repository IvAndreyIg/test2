package sample;

import javafx.beans.property.DoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Chat implements Runnable{
    public TextArea HistoryArea;
    public TextArea OnlineArea;

    PrintWriter out;
    Scanner in;

    public Chat(TextArea HistoryArea,TextArea OnlineArea,PrintWriter out,Scanner in) {
        this.HistoryArea=HistoryArea;
        this.OnlineArea=OnlineArea;
        this.out=out;
        this.in=in;

    }


    @Override
    public void run() {

        while(true){

            out.println("give:History");

            if (in.hasNext()) {
                String serverMessage = in.nextLine();

                if(serverMessage.contains("History:") ) {

                    String[] split = serverMessage.substring(8).split("]");
                    //System.out.println("length:"+split.length);

                    Optional<String> History = Arrays.stream(split).reduce((x, y)->x + "\n" + y);
                    double scrollTop = HistoryArea.getScrollTop();
                    //DoubleProperty doubleProperty = HistoryArea.scrollTopProperty();
                  //  System.out.println("scrollTop:"+scrollTop+"  "+doubleProperty.get());


                    HistoryArea.setText(History.get());


                    HistoryArea.setScrollTop(scrollTop);


                }else
                    if(serverMessage.contains("Online:")){


                        String[] split = serverMessage.substring(7).split("]");
                        //System.out.println("length:"+split.length);

                        Optional<String> Online = Arrays.stream(split).reduce((x, y)->x + "\n" + y);
                        OnlineArea.setText(Online.get());
                    }




            }




            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

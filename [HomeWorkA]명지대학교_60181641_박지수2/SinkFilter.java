/**
 * Copyright(c) 2019 All rights reserved by JU Consulting
 */

import java.io.FileWriter;
import java.io.IOException;

/**
 * Copyright(c) 2013 All rights reserved by JU Consulting
 */

import java.io.IOException;


/**
 * @author Jungho Kim
 * @date 2019
 * @version 1.1
 * @description
 *      �엯�젰媛믪쑝濡� �뱾�뼱�삩 byte�뱾�쓣 �씫�뼱 CS 媛믩쭔 異쒕젰媛� �뙆�씪�뿉 湲곕줉�븯�뒗 �겢�옒�뒪.
 */
public class SinkFilter extends GeneralFilter{
    private String filePath;
    
    public SinkFilter(String outputFilePath) {
        this.filePath = outputFilePath;
    }

    @Override
    public void specificComputationForFilter() throws IOException {
        int checkBlank = 4;
        int databyte = 0;  
        int numOfBlank = 0;
        int idx = 0;
        byte[] buffer = new byte[2069];
        boolean isCS = false;
        
        try {
            FileWriter fw = new FileWriter(this.filePath);
            while(true) {
                while(databyte != '\n' && databyte != -1) {
                    databyte = in.read();
                    if(databyte == ' '){
                        numOfBlank++;
                    }   
                    if(databyte != -1){
                        buffer[idx++] = (byte)databyte;
                    }
                    if(numOfBlank == checkBlank && buffer[idx-3] == 'C' && buffer[idx-2] == 'S'){
                        isCS = true;
                    }
                }
                
                if(isCS == true) {
                    for(int i = 0; i<idx; i++) {
                        fw.write((char)buffer[i]);
                    }
                    isCS = false;
                }
                if(databyte == -1){
                    fw.close();
                    System.out.print( "::Filtering is finished; Output file is created." );
                    return;
                }
                idx = 0;
                numOfBlank = 0;
                databyte = '\0';
            }   
        } catch (Exception e) {
            closePorts();
            e.printStackTrace();
            
        }
    }

}

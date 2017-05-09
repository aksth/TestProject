package com.test.service;

import java.io.File;
import org.springframework.stereotype.Service;

/**
 * Created by intern1 on 5/8/2017.
 */
@Service
public class FileService {

    private static final String ROOT_LOCATION = System.getProperty("catalina.home");

    public void deleteFile(String filename, String pathparam){

        String fullpath = ROOT_LOCATION + File.separator + pathparam + filename;

        try{
            File file = new File(fullpath);
            file.delete();
        }catch(Exception e){

        }
    }
}

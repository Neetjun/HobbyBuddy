package io.github.hobbybuddy.Util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class UploadHandler {


    private String fileName;
    private String filePath;
    private String fileUrl;
    private String extension;
    private int fileNum = 1;

    public ArrayList<String> uploadFile(MultipartFile[] files) throws IOException
    {
        ArrayList<String> result = new ArrayList<String>();

        for(MultipartFile file : files)
        {
            if(!file.getOriginalFilename().equals(""))
            {
                UUID uuid = UUID.randomUUID();
                fileName = file.getOriginalFilename();
                extension = fileName.substring(fileName.lastIndexOf("."),fileName.length());

                fileName = uuid + extension;
                filePath = "C:\\Development\\HobbyBuddy\\upload\\";

                File uploadedFile = new File(filePath, fileName);

                fileUrl = "/img/" + fileName;

                result.add(fileUrl);

                file.transferTo(uploadedFile);
            }
            else
            {
                System.out.println("file doesn't exists");
            }
        }


        return result;
    }
}

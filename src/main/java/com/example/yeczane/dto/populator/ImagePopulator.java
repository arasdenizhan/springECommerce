package com.example.yeczane.dto.populator;

import com.example.yeczane.dto.ProductDto;
import com.example.yeczane.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImagePopulator {
    private ImagePopulator(){
        throw new UnsupportedOperationException();
    }

    public static List<Image> populate(ProductDto productDto){
        List<Image> imageList = new ArrayList<>();
        MultipartFile[] images = productDto.getImages();
        Arrays.stream(images).forEach(multipartFile ->
        {
            try {
                Image image = new Image();
                image.setData(multipartFile.getBytes());
                imageList.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return imageList;
    }
}

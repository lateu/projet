/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lateu.projet.afriland.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author ing-lateu
 */
@ManagedBean
@RequestScoped
public class GallerieBean {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

         for(int i=1;i<=6;i++) {  
          images.add(+ i + ".jpg");  
        } 
       /* images.add("1.jpg");
        images.add("2.jpg");
        images.add("3.jpg");
        images.add("4.jpg");
          images.add("5.jpg");
         images.add("6.jpg");
*/



    }

    public List<String> getImages() {
        return images;
    }
  
}

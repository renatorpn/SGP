/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.SGP.bean;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author Rodrigo
 */
@ManagedBean(name = "uploadBean")
@SessionScoped
public class UploadBean {
    
    private Part file1;

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }
    
    
    public String upload() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();
        String path = scontext.getRealPath("/img/");
        //file1.write("C:\\Users\\lucas\\Documents\\NetBeansProjects\\SGP-18.04\\SGP\\src\\main\\webapp\\img\\" + getFilename(file1));
        file1.write(path + getFilename(file1));
        return null;

  }
  
  private static String getFilename (Part part) {
      for(String cd : part.getHeader("content-disposition").split(";")) {
          if(cd.trim().startsWith("filename")) {
              String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
              return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
          }
      }
      return null;
  }
    
}

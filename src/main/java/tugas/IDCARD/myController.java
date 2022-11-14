/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.IDCARD;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author T.U.F GAMING
 */
@Controller
public class myController {
    
    @RequestMapping(value= "/sendData", method=RequestMethod.POST)
    //@ResponseBody
    public String getData(@RequestParam("mytext") String getText,
                          @RequestParam("myimage") MultipartFile Image, 
                          @RequestParam("tanggal")
                          @DateTimeFormat(pattern="yyyy-MM-dd") Date myDate, Model model) throws IOException{
        
        SimpleDateFormat tanggal = new SimpleDateFormat("EE-dd-MM-yyyy");
        
        String newTanggal = tanggal.format(myDate);
        //String hariIni = getHari(newTanggal);
        
        //String blob = Base64.encodeBase64String(Image.getBytes());
        
        byte[] image = Image.getBytes();
        String base64Image = Base64.encodeBase64String(image);
        String blob = "data:image/png;base64,".concat(base64Image);
        
        model.addAttribute("sentNama", getText);
        model.addAttribute("sentDate", newTanggal);
        model.addAttribute("gambar", blob);
                         
        return "readPage";
        //"Nama : " + getText + "<br>" + "Tanggal Lahir : " + newTanggal + "<br>" + "<img src='data:Image/jpeg;base64," +blob + "' />"+"<br>"
    }
    
    /*public String getHari(String tanggal){
        String[] result = tanggal.split("-");
        if (result[0].equals("Thu")){result[0]="kamis";}
        return result[0];
    }*/
    
}

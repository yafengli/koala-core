package cn.demo.scan.controller;

import cn.demo.pojo.UploadForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * @author yafengli
 */
@Controller
public class UploadContorller {

    @RequestMapping(value = "/upload.ftl", method = RequestMethod.POST)
    public ModelAndView index(@ModelAttribute("type") UploadForm type, BindingResult result, ModelMap model, HttpServletRequest request, HttpServletResponse resp) {

        ModelAndView mav = new ModelAndView("upload");
        try {

            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            MultipartFile mf = req.getFile("upfile");

            /**
            File tmpFile = new File("f:/", mf.getOriginalFilename());
            if (!tmpFile.exists()) {
            tmpFile.getParentFile().mkdirs();
            }
            mf.transferTo(tmpFile);
             */
            File contentFile = new File("f:/", mf.getOriginalFilename());
            File positonFile = new File("f:/", mf.getOriginalFilename() + ".part");


            long size = mf.getSize();
            long contentLength = contentFile.length();
            long startPos = 0;
            long endPos = 0;
            if (contentFile.exists() && size == contentLength) {
                System.out.println("The file is ok.");
                positonFile.deleteOnExit();
            } else {

                RandomAccessFile pcraf = new RandomAccessFile(positonFile, "rw");
                if (!contentFile.exists()) {
                    // set tempFile new start position and end postion
                    pcraf.writeLong(0);
                    pcraf.writeLong(size);
                }
                RandomAccessFile craf = new RandomAccessFile(contentFile, "rw");

                InputStream in = mf.getInputStream();
                pcraf.seek(0);
                startPos = pcraf.readLong();
                endPos = pcraf.readLong();
                System.out.printf("[start=%s,end=%s]\n", startPos, endPos);
                byte[] b = new byte[1024];
                int len = 0;
                in.skip(startPos);
                while ((len = in.read(b)) != -1) {
                    /*Test */
                    if (startPos >= endPos || (type.isStop() && startPos >= 3434)) {
                        System.out.println("Stop...");
                        break;
                    }
                    //*/
                    craf.seek(startPos);
                    craf.write(b, 0, len);
                    startPos += len;
                    // set tempFile new start position and end postion
                    pcraf.seek(0);
                    pcraf.writeLong(startPos);
                }
                pcraf.close();
                craf.close();

                System.out.printf("Current [start=%s,size=%s]\n", startPos, size);
                if (startPos == size) {
                    //删除临时文件
                    if (positonFile.delete()) {
                        System.out.println("Delete the tempfile ok.");
                    } else {
                        System.err.println("Delete the tempfile failed!");
                    }
                }
            }
            System.out.printf("All completed.[%s,%s]\n", contentFile.length(), size);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/upload.ftl", method = RequestMethod.GET)
    public ModelAndView index() {
        System.out.println("GET");
        ModelAndView mav = new ModelAndView("upload");
        mav.addObject("type", new UploadForm());
        return mav;
    }
}

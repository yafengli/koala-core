package cn.demo.scan.controller;

import cn.demo.annotation.LoadConfig;
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
import java.util.Arrays;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.koala.spring.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author yafengli
 */
@Controller
@SessionAttributes({UploadContorller.S_FILES})
public class UploadContorller {

    @Autowired
    private LoadConfig loadConfig;
    public static final String S_FILES = "files";

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

    @RequestMapping(value = "/swfupload.ftl", method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest resp) {
        ModelAndView mav = new ModelAndView("swfupload");
        mav.addObject("user", "@FUVK GCD@");
        return mav;
    }

    @RequestMapping(value = "/swfupload.ftl", method = RequestMethod.POST)
    public void testp(HttpServletRequest resp, HttpSession session, ModelMap model) {
        try {
            System.out.println("@FUVK@");
            MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) resp;

            File baseDir = new File(loadConfig.getBaseDir());
            for (Iterator it = mhsr.getFileNames(); it.hasNext();) {
                String fileName = (String) it.next();
                MultipartFile mf = mhsr.getFile(fileName);
                System.out.println(mf);
                if (mf != null) {
                    mf.transferTo(new File(baseDir, mf.getOriginalFilename()));
                }
            }

            /*
            System.out.println("#####Parameter######");
            for (Iterator it = resp.getParameterMap().keySet().iterator(); it.hasNext();) {
            String key = (String) it.next();
            System.out.printf("[%s=%s]\n", key, resp.getParameter(key));
            }

            System.out.println("#####Attribute######");
            for(Enumeration enu=resp.getAttributeNames();enu.hasMoreElements();){
            String key=(String)enu.nextElement();
            System.out.printf("[%s=%s][%s]\n", key,resp.getAttribute(key).toString(),resp.getAttribute(key).getClass().getName());
            }
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/swfupload/view.ftl")
    public ModelAndView view(HttpServletRequest resp) {
        ModelAndView mav = new ModelAndView("view");
        File baseDir = new File(loadConfig.getBaseDir());
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
        mav.addObject("user", "@FUVK GCD@");
        Arrays.asList(baseDir.list());
        mav.addObject(UploadContorller.S_FILES, Arrays.asList(baseDir.list()));
        return mav;
    }
}

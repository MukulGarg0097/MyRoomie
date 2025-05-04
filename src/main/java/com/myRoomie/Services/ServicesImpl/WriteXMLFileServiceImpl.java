package com.myRoomie.Services.ServicesImpl;

import com.myRoomie.Entities.PropertyEntity;
import com.myRoomie.Repository.IPropertyRepository;
import com.myRoomie.Services.WriteXMLFileService;
import com.myRoomie.request.SiteMapXml;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class WriteXMLFileServiceImpl implements WriteXMLFileService {
    @Autowired
    IPropertyRepository propertyRepo;

    @Override
    public void generateXmlFile() {
        List<String> listPath = getAllPath();
        String mainUrl = "https://www.mroom.in";
        List<SiteMapXml> siteMapXml = generatePath(listPath);
        StringBuilder xmlBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xmlBuilder.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">");
        xmlBuilder.append("<url>");
        xmlBuilder.append("<loc>").append(mainUrl).append("</loc>");
        xmlBuilder.append("<changefreq>monthly</changefreq>");
        xmlBuilder.append("<priority>1.0</priority>");
        xmlBuilder.append(" </url>");
        for (SiteMapXml s : siteMapXml) {
            if (!TextUtils.isEmpty(s.getPath())) {
                xmlBuilder.append("<url>");
                xmlBuilder.append("<loc>").append(mainUrl).append("/").append(s.getPath()).append("</loc>");
                xmlBuilder.append("<changefreq>").append(s.getFreq()).append("</changefreq>");
                xmlBuilder.append("<priority>").append(s.getPriority()).append("</priority>");
                xmlBuilder.append(" </url>");
            }
        }
        xmlBuilder.append("</urlset>");
        stringToDom(xmlBuilder.toString());
    }

    private List<String> getAllPath() {
        List<String> siteMapPath = new ArrayList<>();
        List<PropertyEntity> entities = propertyRepo.findAllByIsActive(true);
        for (PropertyEntity propertyEntity : entities) {
            if (!TextUtils.isEmpty(propertyEntity.getIdProductLocation())) {
                siteMapPath.add("room-details/" + propertyEntity.getIdProductLocation());
            }
        }
        return siteMapPath;
    }

    private void stringToDom(String xmlSource) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("../../../../../../var/www/website/sitemap1.xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<SiteMapXml> fixPath() {
        List<SiteMapXml> list = new ArrayList<>();
        List<String> path = Arrays.asList("about-us", "contact-us", "terms", "privacy-policy", "refund-policy");
        List<String> priority = Arrays.asList("0.8", "0.7", "0.7", "0.7", "0.7");
        for (int i = 0; i < path.size(); i++) {
            list.add(new SiteMapXml(path.get(i), "monthly", priority.get(i)));
        }
        return list;
    }

    private List<SiteMapXml> generatePath(List<String> str) {
        List<SiteMapXml> list = fixPath();
        for (String s : str) {
            list.add(new SiteMapXml(s, "monthly", "0.7"));
        }
        return list;
    }
}

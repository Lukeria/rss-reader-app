package com.course.spp.rss_reader_app.service;

import com.course.spp.rss_reader_app.dto.FeedItemDto;
import com.course.spp.rss_reader_app.domain.Source;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class RssReaderServiceXmlImpl implements RssReaderService {

    @Override
    public List<FeedItemDto> parseFeedsFromSources(List<Source> sources,
                                                   String category,
                                                   int startPosition,
                                                   int size) {
        int offsetCount = 0;
        int remainingCount = size;

        List<FeedItemDto> feedItemDtos = new ArrayList<>();

        for (Source source : sources) {
            if(remainingCount == 0){
                break;
            }

            try (InputStream inputStream = new URL(source.getName()).openStream()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputStream);
                doc.getDocumentElement().normalize();

                NodeList itemNodes = doc.getElementsByTagName("item");

                for (int temp = 0; temp < itemNodes.getLength(); temp++) {
                    if(remainingCount == 0){
                        break;
                    }

                    if(offsetCount != startPosition){
                        offsetCount++;
                        continue;
                    }

                    Node itemNode = itemNodes.item(temp);
                    if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element itemElement = (Element) itemNode;
                        String title = getElementValue(itemElement, "title");
                        String link = getElementValue(itemElement, "link");
                        String description =  getElementValue(itemElement, "description");
                        String pubDate = getElementValue(itemElement,"pubDate");
                        String author = getElementValue(itemElement, "author");
                        NodeList categoryNodes = itemElement.getElementsByTagName("category");

                        List<String> categories = new ArrayList<>();
                        boolean isFoundCategory = false;
                        for (int i = 0; i < categoryNodes.getLength(); i++) {
                            Element categoryElement = (Element) categoryNodes.item(i);
                            String categoryText = categoryElement.getTextContent();
                            categories.add(categoryText);
                            isFoundCategory = categoryText.equalsIgnoreCase(category);
                        }

                        if(category == null || isFoundCategory){
                            FeedItemDto feedItemDto = new FeedItemDto(title, link, description, source.getName(), pubDate, author);
                            feedItemDto.setCategories(categories);
                            feedItemDtos.add(feedItemDto);
                            remainingCount--;
                        }
                    }
                }

            }catch (SAXException | IOException | ParserConfigurationException e) {
                e.printStackTrace();
                throw new RuntimeException("An error occured while parsing source: " + source);
            }
        }

        return feedItemDtos;
    }

    private static String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}

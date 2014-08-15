package com.bn.helper;

import java.net.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.google.android.maps.GeoPoint;

//�Ω�q�����W����樮���u�H�����u����
public class NavigateUtil
{
	//�q�����W�����a�樮���u�H����XML���ɪ���k
	public static Document getPointsroute
	(		
		GeoPoint start,  //�_�I�g�n��
		GeoPoint end     //���I�g�n��
	)
	{
		//�ھڰ_�I���I�g�n�װʺA�ͦ��ШD��URL
		StringBuilder str=new StringBuilder("http://maps.google.com/maps?f=d&hl=en");
		str.append("&saddr=");
		str.append(start.getLatitudeE6()/1E6);
		str.append(",");
		str.append(start.getLongitudeE6()/1E6);
		str.append("&daddr=");
		str.append(end.getLatitudeE6()/1E6);
		str.append(",");
		str.append(end.getLongitudeE6()/1E6);
		str.append("&ie=UTF8&oe=UTF8&output=kml"); 		
		String urlStr=null;
		try
		{
			urlStr=new String(str.toString().getBytes(),"UTF-8");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//�ھ�URL�q�����W���XML����
		Document doc=null;    	
    	try
    	{
    		URL url=new URL(urlStr);
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder=factory.newDocumentBuilder();		    
		    //���XML���ɹﹳ
		    doc=builder.parse(url.openStream());    
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}    	
    	return doc;	
	}
	
	//�qXML���ɤ�������|���`�I���C��
    public static GeoPoint[] getRoutePoints(Document doc)
    {
    	GeoPoint[] result=null;
    	    	
    	String rountPointsStr=null;
    	try
    	{
    		NodeList nld =doc.getElementsByTagName("GeometryCollection");
    		Element gc=(Element)nld.item(0);
    		nld=gc.getElementsByTagName("coordinates");
    		Element coordinates=(Element)nld.item(0);    		
    		rountPointsStr=coordinates.getChildNodes().item(0).getNodeValue();     		
        	
        	String[] sa=rountPointsStr.split(" ");
        	int count=sa.length;
        	
        	GeoPoint[] temp=new GeoPoint[count];
        	
        	for(int i=0;i<count;i++)
        	{
        		String tsa[]=sa[i].split(",");
        		int lon=(int)(Double.parseDouble(tsa[0])*1E6);
        		int lat=(int)(Double.parseDouble(tsa[1])*1E6);
        		temp[i]=new GeoPoint
                (
                		lat, //�n��
                		lon //�g��
                );       		
        	}    
    		result=temp;
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}	
    	return result;
    }
}

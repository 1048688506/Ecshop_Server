package com.ecshop.commodity.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ecshop.commodity.model.Address;
import com.ecshop.commodity.service.AddressService;
import com.ecshop.utils.ExcelViewUtil;
import com.ecshop.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 
 * http://localhost:8080/ecshop/address/getAddressById.do
 * 根据用户id查询用户用户地址信息
 * @author NYXSWL02
 */
@Controller
@RequestMapping("/address")
public class AddressController {
	
	
	@Autowired
	private AddressService addressservice;
	/**
	 * 获取商品分类
	 * @param list
	 * @param department
	 * @param response
	 * @return
	 */
	@RequestMapping("/getAddressById")
	public String getAddressById(HttpServletResponse response,@RequestParam("user_id") String user_id){
		 
		int userId  = Integer.parseInt(user_id);
		List<Address> addressInfo=addressservice.findAddressInfo(userId);
		JSONObject result=new JSONObject();
        
	    JSONArray jsonArray=JSONArray.fromObject(addressInfo);
	    
		result.put("list", jsonArray);

		try {
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 导出Excel
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/Excel")  
    public String exportExcel(HttpServletResponse response) throws IOException  
    {  
         
            ServletOutputStream outputStream = response.getOutputStream();  
            String fileName = new String(("导出excel").getBytes(), "ISO8859_1");  
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式  
            List<String> titles = new ArrayList<String>();
            titles.add("收货人姓名");	
			titles.add("收货地区编号");	
			titles.add("收货地区名");	
			titles.add("收货地址");	
			titles.add("邮政编码");	
			titles.add("移动电话");	
			//查询结果数据
			List<Address> list   = addressservice.exportExcel();
			// 创建一个workbook 对应一个excel应用文件
			XSSFWorkbook workBook = new XSSFWorkbook(); 
			// 在workbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = workBook.createSheet("导出excel");
			ExcelViewUtil excelViewUtil = new ExcelViewUtil(workBook, sheet);
			//设置head样式
			XSSFCellStyle headStyle  = excelViewUtil.getHeadStyle();
			//设置body样式
			XSSFCellStyle bodyStyle = excelViewUtil.getBodyStyle();
			//构建表头
			XSSFRow headRow = sheet.createRow(0);
			//单元格
			XSSFCell cell = null; 
			for (int i =0;i < titles.size();i++)  
	        {  
	            cell = headRow.createCell(i);  
	            cell.setCellStyle(headStyle);  
	            cell.setCellValue(titles.get(i));  
	        }
			 // 构建表体数据（即单元格的value） 
			if (list != null && list.size() > 0)  
	        {  
	            for (int j = 0; j < list.size(); j++)  
	            {  
	                XSSFRow bodyRow = sheet.createRow(j + 1);  
	                Address address = list.get(j);  
	                cell = bodyRow.createCell(0);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(address.getConsignee());  
	  
	                cell = bodyRow.createCell(1);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(address.getRegion_id());  
	  
	                cell = bodyRow.createCell(2);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(address.getRegion_name()); 
	                
	                cell = bodyRow.createCell(3);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(address.getAddress());  
	            
	                cell = bodyRow.createCell(4);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(address.getZipcode());  
	            
	                cell = bodyRow.createCell(5);  
	                cell.setCellStyle(bodyStyle);  
	                cell.setCellValue(address.getPhone_mob());  
	            } 
		            try  
		            {  
		                workBook.write(outputStream);  
		                outputStream.flush();  
		                outputStream.close();  
		            }  
		            catch (IOException e)  
		            {  
		                e.printStackTrace();  
		            }  
		            finally  
		            {  
		                try  
		                {  
		                    outputStream.close();  
		                }  
		                catch (IOException e)  
		                {  
		                    e.printStackTrace();  
		                }  
		            }  
		      
	        }  
        
       
        return null;  
    }  
  
    @RequestMapping(value = "/uplodeExcel") 
    public String upload(HttpServletRequest request, HttpServletResponse response)  
    {  
        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;  
        MultipartFile file = mulRequest.getFile("excel");  
        String filename = file.getOriginalFilename();  
        if (filename == null || "".equals(filename))  
        {  
            return null;  
        }  
        try  
        {  
            InputStream input = file.getInputStream();  
            XSSFWorkbook workBook = new XSSFWorkbook(input);  
            XSSFSheet sheet = workBook.getSheetAt(0);  
            if (sheet != null)  
            {  
                for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)  
                {  
                    XSSFRow row = sheet.getRow(i);  
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++)  
                    {  
                        XSSFCell cell = row.getCell(j);  
                        String cellStr = cell.toString();  
                        System.out.print("【"+cellStr+"】 ");  
                    }  
                    System.out.println();  
                }  
  
            }  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        return null;  
    }  
}

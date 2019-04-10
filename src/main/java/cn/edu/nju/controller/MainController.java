package cn.edu.nju.controller;

import cn.edu.nju.Service.DataService;
import cn.edu.nju.model.DataModel;
import cn.edu.nju.model.LightDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private DataService dataService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("index");
        index.addObject("tempData", getTempData());
        index.addObject("lightData", getLightData());
        return index;
    }

    @ResponseBody
    @RequestMapping("/getTempData")
    public String getTempData(){
        List<DataModel> temperatureDatas = dataService.getTemperatureData();
        StringBuilder result = new StringBuilder("[");
        for (DataModel temperatureData : temperatureDatas) {
            result.append("{\"name\":").append(temperatureData.getTime().getTime()).append(",\"value\":").append(Double.parseDouble(temperatureData.getData())).append("},");
        }
        return result + "]";
    }

    @ResponseBody
    @RequestMapping("/getLightData")
    public String getLightData(){
        List<LightDataModel> temperatureDatas = dataService.getLightData();
        StringBuilder result = new StringBuilder("[");
        for (LightDataModel temperatureData : temperatureDatas) {
            result.append("{\"name\":").append(temperatureData.getTime().getTime()).append(",\"value\":").append(Double.parseDouble(temperatureData.getData())).append("},");
        }
        return result + "]";
    }

    @ResponseBody
    @RequestMapping("/getTempHisData")
    public String getTempHisData(HttpServletRequest request) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date start = simpleDateFormat.parse(request.getParameter("start"));
        Date end = simpleDateFormat.parse(request.getParameter("end"));
        List<DataModel> temperatureDatas = dataService.getTempData(start, end);
        StringBuilder result = new StringBuilder("[");
        for (DataModel temperatureData : temperatureDatas) {
            result.append("{\"name\":").append(temperatureData.getTime().getTime()).append(",\"value\":").append(Double.parseDouble(temperatureData.getData())).append("},");
        }
        return result + "]";
    }

    @ResponseBody
    @RequestMapping("/getLightHisData")
    public String getLightHisData(HttpServletRequest request) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date start = simpleDateFormat.parse(request.getParameter("start"));
        Date end = simpleDateFormat.parse(request.getParameter("end"));
        List<LightDataModel> temperatureDatas = dataService.getLightData(start, end);
        StringBuilder result = new StringBuilder("[");
        for (LightDataModel temperatureData : temperatureDatas) {
            result.append("{\"name\":").append(temperatureData.getTime().getTime()).append(",\"value\":").append(Double.parseDouble(temperatureData.getData())).append("},");
        }
        return result + "]";
    }
}

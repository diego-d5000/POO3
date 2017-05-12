/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poo3;

import java.util.Date;

/**
 *
 * @author diego-d
 */
public class Timer {

    private Date startDate;
    private Date endDate;

    public Timer() {
    }

    public void start() {
        startDate = new Date();
    }
    
    public void end(){
        endDate = new Date();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    public long getDuration(){
        return endDate.getTime() - startDate.getTime();
    }
    
    public void restart(){
        startDate = null;
        endDate = null;
    }

}

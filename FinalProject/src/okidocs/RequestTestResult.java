package okidocs;

import javax.swing.*;

public class RequestTestResult {
    
    public static void request(JPanel parent){

        add(new HeaderPanel(app, app::showHomePage), BorderLayout.NORTH);
        
        int studentId = Session.getStudentId();

        if (studentId == -1){
            JOptionPane.showMessageDialog(parent, "Please login first.");
            return;
        }

        //TEMP: TO BE CHANGED
        boolean requested = TestResultDAO.requestTestResult(studentId);

        if(requested){
            JOptionPane.showMessageDialog(parent, "Request submitted. \n Results will be available in 5 to 10 business days.");
        } else {
            JOptionPane.showMessageDialog(parent, "You have already requested your test result.");
        }


    }
}

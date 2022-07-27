package view.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import view.common.MyADFUtil;

public class LoginBean {
    private RichInputText usernameInputText;
    private RichInputText passwordInputText;

    public LoginBean() {
    }

    public void setUsernameInputText(RichInputText usernameInputText) {
        this.usernameInputText = usernameInputText;
    }

    public RichInputText getUsernameInputText() {
        return usernameInputText;
    }

    public void setPasswordInputText(RichInputText passwordInputText) {
        this.passwordInputText = passwordInputText;
    }

    public RichInputText getPasswordInputText() {
        return passwordInputText;
    }

    public String loginAction() {
        String username = usernameInputText.getValue().toString();
        String password = passwordInputText.getValue().toString();
        
        PreparedStatement stat = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            String sql = "select school_principal_name from school_information where school_principal_username='" + username + "' and school_principal_pasword='" + password + "'";
            
            conn = MyADFUtil.getConnection();
            stat = conn.prepareStatement(sql);
            rs = stat.executeQuery();
            
            while (rs.next()) {
                MyADFUtil.putInSessionScope("username", username);
                MyADFUtil.putInSessionScope("principalName", rs.getString(1));
                
                return "dashboard";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        MyADFUtil.showErrorMessage("Invalid username or password");
        return null;
    }
}

package reggensc.poschtiapp.web.springsecurity;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;

@Component
public class LoginHelper {

    public void updateMessages() throws Exception {
        Map<String, String> parameterMap = FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap();

        if (parameterMap.containsKey("error")) {
            Exception ex = (Exception) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                    .get(WebAttributes.AUTHENTICATION_EXCEPTION);

            if (ex != null) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email or password. Please try again.",
                                "Invalid email or password. Please try again."));
            }
        }
    }
}

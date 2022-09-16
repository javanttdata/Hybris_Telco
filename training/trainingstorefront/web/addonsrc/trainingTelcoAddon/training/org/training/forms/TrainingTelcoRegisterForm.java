package training.org.training.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;

import java.util.Date;

public class TrainingTelcoRegisterForm extends RegisterForm {

    private String cpf;

    private String rg;

    private Date birthDate;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

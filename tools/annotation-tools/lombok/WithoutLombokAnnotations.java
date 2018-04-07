import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WithoutLombokAnnotations {
    private boolean flag;
    private final int number;
    private final String text;
    private List<String> strList;
 
    public WithoutLombokAnnotations(int number, String text) {
        this.number = number;
        this.text = text;
    }
 
    public boolean isFlag() {
        return flag;
    }
 
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
 
    public List<String> getStrList() {
        if (strList == null) {
            strList = new ArrayList<>(128);
        }
        return Collections.unmodifiableList(strList);
    }
 
    public void setStrList(List<String> strList) {
        this.strList = strList;
    }
 
    public int getNumber() {
        return number;
    }
 
    public String getText() {
        return text;
    }
 
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.number;
        hash = 11 * hash + Objects.hashCode(this.text);
        return hash;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WithoutLombokAnnotations other = (WithoutLombokAnnotations) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return "WithoutLombokAnnotations{" + "flag=" + flag + ", number=" + number + ",text=" + text + "}";
    }
}

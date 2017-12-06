import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@lombok.Getter
@lombok.Setter
public class GettersAndSetters {
    private boolean flag;
    private final int number;
    private final String text;
    private List<String> strList;
 
    public GettersAndSetters(int number, String text) {
        this.number = number;
        this.text = text;
    }
 
    public List<String> getStrList() {
        if (strList == null) {
            strList = new ArrayList<>(128);
        }
        return Collections.unmodifiableList(strList);
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
        final GettersAndSetters other = (GettersAndSetters) obj;
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
        return "WithoutLombokAnnotations{" + "flag=" + flag + ", number=" + number + ",text=" + text + '}';
    }
}

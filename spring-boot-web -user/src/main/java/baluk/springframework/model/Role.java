package baluk.springframework.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
@Entity
public class Role extends AbstractModelClass {
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Holder> holders = new ArrayList<>();

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Holder> getHolders() {
        return holders;
    }

    public void setHolders(List<Holder> holders) {
        this.holders = holders;
    }

    public void addUser(Holder holder) {
        if(!this.holders.contains(holder)){
            this.holders.add(holder);
        }
        if(!holder.getRoles().contains(this)) {
            holder.getRoles().add(this);
        }
    }
    public void removeUser(Holder holder) {
        this.holders.remove(holder);
        holder.getRoles().remove(this);
    }
}

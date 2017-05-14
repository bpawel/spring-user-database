package baluk.springframework.converters;

import baluk.springframework.model.Holder;
import baluk.springframework.services.security.HolderDetailsImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
@Component
public class HolderToHolderDetails implements Converter<Holder, UserDetails> {
    @Override
    public UserDetails convert(Holder holder) {
        HolderDetailsImpl userDetails = new HolderDetailsImpl();

        if(holder != null){
            userDetails.setUsername(holder.getUsername());
            userDetails.setPassword(holder.getEncryptedPassword());
            userDetails.setEnabled(holder.getEnabled());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            holder.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getRole()));
            });
            userDetails.setAuthorities(authorities);
        }
        return userDetails;
    }
}

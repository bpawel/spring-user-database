package baluk.springframework.services.security;

import baluk.springframework.model.Holder;
import baluk.springframework.services.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("holderDetailsService")
public class HolderDetailsServiceImpl implements UserDetailsService {

    private HolderService holderService;
    private Converter<Holder, UserDetails> userUserDetailsConverter;

    @Autowired
    public void setHolderService(HolderService holderService) {
        this.holderService = holderService;
    }

    @Autowired
    @Qualifier(value = "holderToHolderDetails")
    public void setUserUserDetailsConverter(Converter<Holder, UserDetails> userUserDetailsConverter) {
        this.userUserDetailsConverter = userUserDetailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userUserDetailsConverter.convert(holderService.findByUsername(username));
    }
}

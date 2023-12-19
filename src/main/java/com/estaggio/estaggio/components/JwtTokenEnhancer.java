package com.estaggio.estaggio.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.estaggio.estaggio.entities.Usuario;
import com.estaggio.estaggio.repositories.UsuarioRepository;

/*Criamos um componete que implementa a interface de Token
 * do Spring - TokenEnhancer
 * Utilizamos para adicionar informações do usuário que vai ser introduzido no payload do token
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @Autowired
	private UsuarioRepository usuarioRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
       Usuario user = usuarioRepository.findByEmail(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
		map.put("userFirstName", user.getFirstName());
		map.put("userId", user.getId());

		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		token.setAdditionalInformation(map);
		
		return accessToken;
    }
    
}

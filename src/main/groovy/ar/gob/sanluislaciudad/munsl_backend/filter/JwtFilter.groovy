package ar.gob.sanluislaciudad.munsl_backend.filter

import ar.gob.sanluislaciudad.munsl_backend.service.JwtService
import ar.gob.sanluislaciudad.munsl_backend.service.impl.UserDetailsServiceImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.ApplicationContext
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter extends OncePerRequestFilter{

	private final JwtService jwtService
	private final ApplicationContext applicationContext

	JwtFilter(JwtService jwtService, ApplicationContext applicationContext) {
		this.jwtService = jwtService
		this.applicationContext = applicationContext
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization")

		if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response)
			return
		}

		String token = authorizationHeader.substring(7)
		String username = jwtService.extractUsername(token)

		if(Objects.isNull(username) || Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())){
			filterChain.doFilter(request, response)
			return
		}

		UserDetails userDetails = applicationContext.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username)
		if(!jwtService.validateToken(token, userDetails)){
			filterChain.doFilter(request, response)
			return
		}

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails,
				null,
				userDetails.getAuthorities()
				)

		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request))
		SecurityContextHolder.getContext().setAuthentication(authenticationToken)
		filterChain.doFilter(request,response)
	}
}

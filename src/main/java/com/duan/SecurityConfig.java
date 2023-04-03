package com.duan;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.duan.entity.Account;
import com.duan.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;
    @Lazy
    @Autowired
    BCryptPasswordEncoder pe;


    /* Cung cấp nguồn dữ liệu đăng nhập */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Account user = accountService.findById(username);    /* Tải user từ db */
                String password = pe.encode(user.getPassword());    /* Mã hóa password */
                String[] roles = user.getAuthorities().stream()    /* Lấy các vai trò của người dùng -> đổi thành mảng */
                        .map(er -> er.getRole().getId()).toList().toArray(new String[0]); /* Đổi thành mảng */
                return User.withUsername(username).password(password).roles(roles).build();
            } catch (NoSuchElementException e) { /* User không tồn tại ngoại lệ */
                throw new UsernameNotFoundException(username + "Không tìm thấy !");
            }
        });
    }

    /* Phân quyền */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("STAF", "DIRE")
                .antMatchers("/rest/authorities").hasRole("DIRE")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/security/login/form")  /* địa chỉ form đăng nhập */
                .loginProcessingUrl("/security/login") /* địa chỉ khi nhất đăng nhập */
                .defaultSuccessUrl("/security/login/success", false) /* địa chỉ xử lý đăng nhập khi thành công - False: Đăng nhập thành công không nhất thiết quay về trang success*/
                .failureUrl("/security/login/error"); /* địa chỉ xử lý đăng nhập khi lỗi */

        http.rememberMe()
                .tokenValiditySeconds(86400); /* xác định thời gian tồn tại cookies (thời gian tự động login có hiệu lực) */

        http.exceptionHandling()
                .accessDeniedPage("/security/unauthoried"); /* địa chỉ khi đăng nhập đường dẫn chưa cấp quyền - VD: CUSTOMER đăng nhập đường dẫn của DIRE*/

        http.logout()
                .logoutUrl("/security/logoff") /* địa chỉ đường dẫn khi đăng xuất */
                .logoutSuccessUrl("/security/logoff/success"); /* địa chỉ xử lý dăng xuất khi thành công */
    }

    /* Cơ chế mã hóa mật khẩu */
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* Cho phép truy xuất REST API từ bên ngoài (domain khác ) */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

}

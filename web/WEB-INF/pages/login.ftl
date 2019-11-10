<#include "base.ftl"/>
<#macro head>
    <title>Login</title>
</#macro>
<#macro content>
    <div class="login-register-section section pt-90 pt-lg-70 pt-md-60 pt-sm-55 pt-xs-45  pb-70 pb-lg-50 pb-md-40 pb-sm-30 pb-xs-20">
        <div class="container">
            <div class="row"  style="margin-left: 400px; margin-top: 30px;  margin-bottom: 250px">
                <!--Login Form Start-->
                <div class="col-md-6 col-sm-6">
                    <div class="customer-login-register">
                        <div class="form-login-title">
                            <h2>Login</h2>
                        </div>
                        <div class="login-form">
                            <form method="post">
                                <div class="form-fild">
                                    <p><label>Username or email address <span class="required">*</span></label></p>
                                    <input name="email" type="text">
                                </div>
                                <div class="form-fild">
                                    <p><label>Password <span class="required">*</span></label></p>
                                    <input name="password" type="password">
                                </div>
                                <div class="login-submit">
                                    <button type="submit" class="btn">Sign in</button>
                                    <label>
                                        <input class="checkbox" name="remember" type="checkbox">
                                        <span>Remember me</span>
                                    </label>
                                </div>
                                <div class="login-submit">
                                    <button type="submit" class="btn"><a href="/registration">Register</a></button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>
<@main/>
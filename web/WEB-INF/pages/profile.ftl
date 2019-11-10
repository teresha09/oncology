<#include "base.ftl"/>
<#macro head>
    <title>Profile</title>
</#macro>
<#macro content>
    <div class="row align-items-center" style="margin-left: 400px; margin-top: 30px">
        <ul>
            <li><font size="5">My Account </font></li>
            <li>
                <div class="card" style="width: 50rem; height: 30rem; margin-top: 20px;">
                    <div class="card-header" >
                        <div class="header-menu-area text-center">
                            <nav class="main-menu">
                                <ul>
                                    <li><a href="/logout">Logout</a></li>
                                    <li class="ml-auto">
                                        <div class="card-footer" style="background: 0px">
                                            <button type="submit" class="btn" style="background-color: #003"><a href="/addpost">Добавить пост</a></button>
                                        </div>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                    <div class="row">

                    </div>
                    <div class="card-body" >
                        <div class="row">
                            <form class="col-xl-8">
                                <div class="form-group">
                                    <p><b>Email</b></p>
                                    <p>${User.getEmail()}</p>
                                </div>
                                <div class="form-group">
                                    <p><b>Полное имя</b></p>
                                    <p>${User.getName()} ${User.getSurname()}</p>
                                </div>
                                <button type="submit" class="btn btn-primary" style="background-color: #003"><a href="/edit">Редактировать</a></button>
                            </form>
                            <div class="col-sm-4">
                                <div class="card" style="width: 15rem; height: 15rem; margin-top: 10px;">
                                    <div class="card-body">
                                        <img src="${User.getProfilePicture()}"  class="card-img-left" >
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</#macro>
<@main/>
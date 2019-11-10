<#include "base.ftl"/>
<#macro head>
    <title>Login</title>
</#macro>
<#macro content>
    <div class="row align-items-center" style="margin-left: 400px; margin-top: 30px">
        <ul>
            <li><font size="5">My Account </font></li>
            <li>
                <div class="card" style="width: 50rem; height: 40rem; margin-top: 20px;">
                    <div class="card-header" >
                        <div class="header-menu-area text-center">
                            <nav class="main-menu">
                                Редактировать
                            </nav>
                        </div>
                    </div>
                    <div class="row">

                    </div>
                    <div class="card-body" >
                        <div class="row">
                            <form class= col-xl-12 method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Email address</label>
                                    <input name="email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
                                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Name</label>
                                    <input name="name" type="text" class="form-control" id="exampleInputPassword1" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Surname</label>
                                    <input name="surname" type="text" class="form-control" placeholder="Surname">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Change Avatar</label>
                                    <input name="file" type="file" class="form-control" placeholder="file">
                                </div>
                                <button type="submit" class="btn btn-primary" style="background-color: #003">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </li>
        </ul>

    </div>
</#macro>
<@main/>
<#include "base.ftl"/>
<#macro head>
    <title>Add Post</title>
</#macro>
<#macro content>
    <div class="row align-items-center" style="margin-left: 400px; margin-top: 30px">
        <div class="card" style="width: 60rem;">
            <form class="md-form" method="post" enctype="multipart/form-data">
                <div class="file-field">
                    <div class="z-depth-1-half mb-4 top-cover center-block" style="margin-left: 330px" >
                        <img src="https://mdbootstrap.com/img/Photos/Others/placeholder.jpg" class="img-fluid"
                             alt="example placeholder" style="height: 200px;">
                    </div>
                    <div class="d-flex justify-content-center">
                        <div class="btn btn-mdb-color btn-rounded float-left">
                            <input name="file" type="file">
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <h5 class="card-title"><input name="title" value="" type="text" placeholder="Заголовок статьи" style="width: 100%"></h5>
                    <p class="card-text"><textarea name="text"  type="text" style="width: 100%; height: 100%"></textarea></p>

                </div>
                <div class="card-body row align-items-center">
                    <div class="form-group">
                        <select class="form-control" name="category">
                            <option value="Рак">Рак</option>
                            <option value="Лечение">Лечение</option>
                            <option value="Диагностика">Диагностика</option>
                            <option value="Новости">Новости</option>
                        </select>
                    </div>
                    <div class="login-submit mr-auto">
                        <button type="submit" class="btn">Submit</button>
                    </div>

                    </div>
                </div>
            </form>
        </div>
    </div>
</#macro>
<@main/>
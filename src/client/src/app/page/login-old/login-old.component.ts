import      { Component, OnInit }     from '@angular/core';
declare let $          : any;
declare let jwt_decode :  any;


let TOKEN_KEY;
let $notLoggedIn;
let $loggedIn;
let $loggedInBody;
let $response;
let $login;
let $userInfo;

let protocol = "https:";

// let host     = "api.pos.jabaridash.com";
let host     = "137.140.146.1";
let port     = "8443";
let base     = `${protocol}//${host}:${port}`;
let urls = {
  user     : `${base}/user`,
  persons  : `${base}/products`,
  protect  : `${base}/users`,
  auth     : `${base}/auth`
};

@Component({
  selector     : 'app-login',
  templateUrl  : './login-old.component.html',
  styleUrls    : ['./login-old.component.css']
})
export class LoginOldComponent implements OnInit {

  constructor() {}

  getJwtToken() {
    return localStorage.getItem(TOKEN_KEY);
  }

  setJwtToken(token) {
    localStorage.setItem(TOKEN_KEY, token);
  }

  removeJwtToken() {
    localStorage.removeItem(TOKEN_KEY);
  }

  doLogin(loginData) {
    $.ajax({

      url        :         urls.auth,
      type       :        "POST",
      data       :        JSON.stringify(loginData),
      contentType: "application/json; charset=utf-8",
      dataType   :    "json",

      success: (data, textStatus, jqXHR) => {

        console.log(data);
        this.setJwtToken(data.token);
        $login.hide();
        $notLoggedIn.hide();
        this.showTokenInformation();
        this.showUserInformation();
      },

      error: function (jqXHR, textStatus, errorThrown) {

        if (jqXHR.status === 401 || jqXHR.status === 403) {

          alert( jqXHR.responseText);

        } else {

          throw new Error("an unexpected error occurred: " + errorThrown);
        }
      }
    });
  }

  doLogout() {

    this.removeJwtToken();
    $login.show();
    $userInfo
      .hide()
      .find("#userInfoBody").empty();
    $loggedIn.hide();
    $loggedInBody.empty();
    $notLoggedIn.show();
  }

  createAuthorizationTokenHeader() {

    let token = this.getJwtToken();

    if (token) {

      return {"Authorization": "Bearer " + token};

    } else {

      return {};
    }
  }

  showUserInformation() {
    $.ajax({
      // url: "/user",
      url        :         urls.user,
      type       :        "GET",
      contentType: "application/json; charset=utf-8",
      dataType   :    "json",
      headers    : this.createAuthorizationTokenHeader(),

      success    : function (data, textStatus, jqXHR) {
        let $userInfoBody = $userInfo.find("#userInfoBody");

        $userInfoBody.append($("<div>").text("Username: " + data.username));
        $userInfoBody.append($("<div>").text("Email: " + data.email));

        let $authorityList = $("<ul>");
        data.authorities.forEach(function (authorityItem) {
          $authorityList.append($("<li>").text(authorityItem.authority));
        });
        let $authorities = $("<div>").text("Authorities:");
        $authorities.append($authorityList);

        $userInfoBody.append($authorities);
        $userInfo.show();
      }
    });
  }

  showTokenInformation() {
    let jwtToken = this.getJwtToken();
    let decodedToken = jwt_decode(jwtToken);

    $loggedInBody.append($("<h4>").text("Token"));
    $loggedInBody.append($("<div>").text(jwtToken).css("word-break", "break-all"));
    $loggedInBody.append($("<h4>").text("Token claims"));

    let $table = $("<table>")
      .addClass("table table-striped");
    this.appendKeyValue($table, "sub", decodedToken.sub);
    this.appendKeyValue($table, "iat", decodedToken.iat);
    this.appendKeyValue($table, "exp", decodedToken.exp);

    $loggedInBody.append($table);

    $loggedIn.show();
  }

  appendKeyValue($table, key, value) {
    let $row = $("<tr>")
      .append($("<td>").text(key))
      .append($("<td>").text(value));
    $table.append($row);
  }

  showResponse(statusCode, message) {
    $response
      .empty()
      .text("status code: " + statusCode + "\n-------------------------\n" + message);
  }

  ngOnInit() {

    $notLoggedIn  = $("#notLoggedIn");
    $loggedIn     = $("#loggedIn").hide();
    $loggedInBody = $("#loggedInBody");
    $response     = $("#response");
    $login        = $("#login");
    $userInfo     = $("#userInfo").hide();

    // REGISTER EVENT LISTENERS =============================================================
    $("#loginForm").submit( (event) => {
      event.preventDefault();

      let $form = $("#loginForm");

      let formData = {
        username   : $form.find('input[name="username"]').val(),
        password   : $form.find('input[name="password"]').val()
      };

      this.doLogin(formData);
    });

    $("#logoutButton").click(() => this.doLogout());

    $("#exampleServiceBtn").click( ()=> {
      $.ajax({

        url        :         urls.persons,
        type       :        "GET",
        contentType: "application/json; charset=utf-8",
        dataType   : "json",
        headers    : this.createAuthorizationTokenHeader(),

        success: (data, textStatus, jqXHR)=> {
          this.showResponse(jqXHR.status, JSON.stringify(data));
        },

        error: (jqXHR, textStatus, errorThrown) => {
          this.showResponse(jqXHR.status, errorThrown);
        }
      });
    });

    $("#adminServiceBtn").click( () => {
      $.ajax({

        url        :         urls.protect,
        type       :        "GET",
        contentType: "application/json; charset=utf-8",
        headers    :     this.createAuthorizationTokenHeader(),

        success    : (data, textStatus, jqXHR) => {
          this.showResponse(jqXHR.status, data);
        },

        error: (jqXHR, textStatus, errorThrown)=>  {
          this.showResponse(jqXHR.status, errorThrown);
        }
      });
    });

    $loggedIn.click(function () {
      $loggedIn
        .toggleClass("text-hidden")
        .toggleClass("text-shown");
    });

    // INITIAL CALLS =============================================================
    if (this.getJwtToken()) {
      $login.hide();
      $notLoggedIn.hide();
      this.showTokenInformation();
      this.showUserInformation();
    }

  }

}

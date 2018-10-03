/**
 * Redirects to the swagger spec.
 */
function redirect() {

    let protocol = window.location.protocol;
    let host     = window.location.host;
    let location = `${protocol}//${host}/swagger-ui.html`;

    console.log(`Redirecting from ${window.location} to ${location}`);

    window.location = location;
}
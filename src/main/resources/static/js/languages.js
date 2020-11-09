function changeLanguage(){
    window.location = window.location.origin + window.location.pathname + "?language=" + $("#lan").val();
}
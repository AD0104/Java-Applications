$(".table_row_location").on("click", (e) => {
    let location_id = $(e.currentTarget).children(".location_id").text();
    let current_url = location.href;
    location.href = current_url + "/" + location_id;
});

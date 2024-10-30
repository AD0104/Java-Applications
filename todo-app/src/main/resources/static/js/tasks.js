// $('input.task-toggler').click((element) => {
//     let button = element.currentTarget;
//     let isFinished = button.checked;
//     let taskId = $(button).siblings("p.taskId").first().text();
//     $.post(`/user/update-task/${taskId}/${isFinished}`, {},
//         function (data, textStatus, jqXHR) {

//         },
//         "dataType"
//     );
// })
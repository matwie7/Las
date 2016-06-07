/**
 * Created by Development on 07-Jun-16.
 */
$('tr').on('click', function (e) {
    window.open('update_invoice.jsp?id=' + (this.id));
});

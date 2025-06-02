const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

function addAnnouncement(type, message) {
    const now = new Date().toLocaleTimeString();
    const alertType = type === 'New Book' ? 'primary' : 'success'; // You can change color mappings here

    const alert = `
        <div class="alert alert-${alertType} alert-dismissible fade show mt-3" role="alert">
            <strong>${type}:</strong> ${message} <span class="text-muted small">(${now})</span>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    `;

    document.getElementById("announcementsContainer").insertAdjacentHTML("afterbegin", alert);
}


stompClient.connect({}, function () {
    stompClient.subscribe('/topic/books', function (msg) {
        addAnnouncement('New Book', msg.body);
    });

    stompClient.subscribe('/topic/returns', function (msg) {
        addAnnouncement('Returned Book', msg.body);
    });
});
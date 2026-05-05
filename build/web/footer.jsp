<!DOCTYPE html>
<html lang="en">
    
    <body>
        <div class="container-fluid bg-primary text-white pb-3">
            <div class="container">
                <h2 class="text-center">Contact</h2>
                <p class="text-center">We love our fans!</p>
                
                <div class="row">
                    <div class="col-md-4 mt-1">
                        <p><i class="fa-solid fa-phone p-2"></i> Phone : +91 7394960743</p>
                        <p>
                            <i class="fa-solid fa-envelope p-2"></i> Email :
                            rayeesali1510@gmail.com
                        </p>
                        <p>
                            <i class="fa-solid fa-signs-post p-2"></i> Postal Code : 462022
                        </p>
                    </div>

                    <div class="col-md-8 mt-1">
                        <form action="./ContactInformationServlet">
                            <div class="row">
                                <div class="col-sm-6 form-group mt-1">
                                    <input
                                        name="name"
                                        type="text"
                                        class="form-control"
                                        placeholder="name"
                                        required
                                        />
                                </div>

                                <div class="col-sm-6 form-group mt-1">
                                    <input
                                        name="email"
                                        type="email"
                                        class="form-control"
                                        placeholder="email"
                                        required
                                        />
                                </div>
                            </div>
                            <textarea
                                name="message"
                                id="message"
                                rows="5"
                                class="form-control mt-1"
                                placeholder="Enter your message...."
                                ></textarea>

                            <div class="row">
                                <div class="col-md-12 form-group mt-1">
                                    <button type="submit" class="btn btn-warning" name="send" value="send">Send</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
</html>

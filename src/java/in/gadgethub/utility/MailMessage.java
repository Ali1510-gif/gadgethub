package in.gadgethub.utility;

import javax.mail.MessagingException;

public class MailMessage {

    public static void registrationSuccess(String recipientMailId, String name) throws MessagingException {
        String subject = "Registration Successfull";
        String htmlTextMessage = "" + "<html>" + "<body>"
                + "<h2 style='color:green;'>Welcome to " + AppInfo.appName + "</h2>" + "" + "Hi " + name + ","
                + "<br><br>Thanks for singing up with " + AppInfo.appName + ".<br>"
                + "We are glad that you choose us. We invite you to check out our latest collection of new electonics appliances."
                + "<br>We are providing upto 60% OFF on most of the electronic gadgets. So please visit our site and explore the collections."
                + "<br><br>Our Online electronics is growing in a larger amount these days and we are in high demand so we thanks all of you for "
                + "making us up to that level. We Deliver Product to your house with no extra delivery charges and we also have collection of most of the"
                + "branded items.<br><br>As a Welcome gift for our New Customers we are providing additional 10% OFF Upto 500 Rs for the first product purchase. "
                + "<br>To avail this offer you only have "
                + "to enter the promo code given below.<br><br><br> PROMO CODE: " + "" + AppInfo.appName.toUpperCase() + "500<br><br><br>"
                + "Have a good day!<br>" + "" + "</body>" + "</html>";
        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }
//    public static void registrationSuccess(String recipientMailId, String name) throws MessagingException {
//        String subject = "Welcome to the World of Excellence at " + AppInfo.appName;
//        String htmlTextMessage = ""
//                + "<html><head>"
//                + "<style>"
//                + "body { font-family: 'Georgia', serif; margin: 0; padding: 0; background-color: #f8f9fa; color: #444; }"
//                + ".email-container { max-width: 800px; margin: 30px auto; background: #ffffff; border-radius: 15px; overflow: hidden; box-shadow: 0 8px 30px rgba(0, 0, 0, 0.2); }"
//                + ".header { background-image: linear-gradient(to right, #8e44ad, #3498db); color: #ffffff; padding: 40px 30px; text-align: center; }"
//                + ".header h1 { font-size: 36px; margin: 0; font-weight: bold; }"
//                + ".header p { font-size: 18px; margin-top: 10px; }"
//                + ".username { background: linear-gradient(to right, #ff7e5f, #feb47b); -webkit-background-clip: text; -webkit-text-fill-color: transparent; font-weight: bold; font-size: 28px; }"
//                + ".content { padding: 30px; }"
//                + ".content h2 { font-size: 28px; color: #1e3c72; margin-bottom: 20px; }"
//                + ".content p { line-height: 1.8; font-size: 16px; margin: 15px 0; color: #555; }"
//                + ".highlight { color: #2a5298; font-weight: bold; }"
//                + ".promo { background-color: #fef7e7; padding: 25px; border-radius: 10px; border: 1px dashed #ffc107; text-align: center; font-size: 18px; margin: 25px 0; }"
//                + ".promo-code { color: #ffc107; font-size: 22px; font-weight: bold; }"
//                + ".cta { text-align: center; margin: 40px 0; }"
//                + ".cta .button { display: inline-block; padding: 14px 28px; background-color: #2a5298; color: #ffffff; text-decoration: none; font-size: 18px; font-weight: bold; border-radius: 8px; transition: all 0.3s ease; }"
//                + ".cta .button:hover { background-color: #1e3c72; }"
//                + ".footer { background-image: linear-gradient(to right, #3498db, #8e44ad); color: #ffffff; text-align: center; padding: 20px; font-size: 14px; margin-top: 30px; }"
//                + ".footer a { color: #ffffff; text-decoration: none; font-weight: bold; }"
//                + "ul { padding-left: 20px; list-style-type: disc; }"
//                + "</style>"
//                + "</head><body>"
//                + "<div class='email-container'>"
//                + "<div class='header'>"
//                + "<h1>Welcome, <span class='username'>" + name + "</span>!</h1>"
//                + "<p>Your journey to innovation and excellence begins now.</p>"
//                + "</div>"
//                + "<div class='content'>"
//                + "<h2>Congratulations on Joining " + AppInfo.appName + "</h2>"
//                + "<p>We are thrilled to welcome you to the world of premium electronics! At <span class='highlight'>" + AppInfo.appName + "</span>, we are committed to delivering top-tier products with unmatched quality and exceptional service.</p>"
//                + "<p>Here’s what awaits you:</p>"
//                + "<ul>"
//                + "<li><strong>Exclusive Access:</strong> Be the first to explore our cutting-edge electronic gadgets and limited-edition collections.</li>"
//                + "<li><strong>Luxury Discounts:</strong> Enjoy up to <span class='highlight'>60% OFF</span> on your favorite products.</li>"
//                + "<li><strong>Free Delivery:</strong> We offer complimentary doorstep delivery, no hidden charges.</li>"
//                + "<li><strong>Quality Guaranteed:</strong> Premium electronics from trusted brands, ensuring durability and satisfaction.</li>"
//                + "<li><strong>24/7 Support:</strong> Our team is always here to assist you, ensuring a seamless shopping experience.</li>"
//                + "</ul>"
//                + "<p>As a token of appreciation, we're offering you an exclusive <span class='highlight'>10% OFF</span> (up to ₹500) on your first purchase! Use the promo code below to claim your gift:</p>"
//                + "<div class='promo'>"
//                + "PROMO CODE: <span class='promo-code'>" + AppInfo.appName.toUpperCase() + "500</span>"
//                + "</div>"
//                + "<p>But that’s not all! As a valued member, you'll also get:</p>"
//                + "<ul>"
//                + "<li>Early notifications about new arrivals, sales, and exclusive offers.</li>"
//                + "<li>Invitations to VIP events, contests, and giveaways.</li>"
//                + "<li>Expert tips and tricks to get the most out of your gadgets and appliances.</li>"
//                + "</ul>"
//                + "<div class='cta'><a href='https://your-app-link.com/explore' class='button'>Start Exploring</a></div>"
//                + "</div>"
//                + "<div class='footer'>"
//                + "<p>&copy; " + java.time.Year.now() + " " + AppInfo.appName + ". All rights reserved.</p>"
//                + "<p>If you have any questions, feel free to <a href='https://your-app-link.com/contact'>contact us</a>.</p>"
//                + "</div>"
//                + "</div>"
//                + "</body></html>";
//
//        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
//    }

    public static void loginSuccess(String recipientMailId, String name) throws MessagingException {
        String subject = "Login Successful";
        String htmlTextMessage = ""
                + "<html><head>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
                + ".header { background: linear-gradient(135deg, #6c63ff, #4CAF50); color: #ffffff; padding: 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 24px; }"
                + ".content { padding: 20px; }"
                + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
                + ".cta { text-align: center; margin: 20px 0; }"
                + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
                + ".button:hover { background-color: #45a049; }"
                + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
                + ".footer a { color: #6c63ff; text-decoration: none; }"
                + "</style>"
                + "</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>Welcome Back to " + AppInfo.appName + "</h1></div>"
                + "<div class='content'>"
                + "<p>Hi <strong>" + name + "</strong>,</p>"
                + "<p>We noticed a successful login to your account. If this was you, there's nothing to worry about!</p>"
                + "<p>If this wasn't you, please secure your account immediately by changing your password or contacting our support team.</p>"
                + "<div class='cta'><a href='https://your-app-link.com/support' class='button'>Secure Your Account</a></div>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any questions, please <a href='https://your-app-link.com/contact'>contact us</a>.</p>"
                + "<p>&copy; " + java.time.Year.now() + " " + AppInfo.appName + ". All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }

    public static void sendPartialOrderNotification(String recipientMailId, String name, String productName, int orderedQuantity, int availableQuantity) throws MessagingException {
        String subject = "Partial Order Fulfillment Notification";
        String htmlTextMessage = ""
                + "<html><head>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
                + ".header { background: linear-gradient(135deg, #6c63ff, #4CAF50); color: #ffffff; padding: 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 24px; }"
                + ".content { padding: 20px; }"
                + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
                + ".cta { text-align: center; margin: 20px 0; }"
                + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
                + ".button:hover { background-color: #45a049; }"
                + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
                + ".footer a { color: #6c63ff; text-decoration: none; }"
                + "</style>"
                + "</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>Partial Order Fulfillment</h1></div>"
                + "<div class='content'>"
                + "<p>Hi <strong>" + name + "</strong>,</p>"
                + "<p>Thank you for ordering <strong>" + productName + "</strong>.</p>"
                + "<p>We noticed that you ordered <strong>" + orderedQuantity + "</strong> units, but only <strong>" + availableQuantity + "</strong> units are available in stock at the moment.</p>"
                + "<p>To avoid delays, we will send the <strong>" + availableQuantity + "</strong> units immediately. The remaining <strong>" + (orderedQuantity - availableQuantity) + "</strong> units will be sent as soon as they are back in stock.</p>"
                + "<p>We will notify you when the remaining stock is available for shipping. Thank you for your patience!</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any questions, please <a href='https://your-app-link.com/contact'>contact us</a>.</p>"
                + "<p>&copy; " + java.time.Year.now() + " " + AppInfo.appName + ". All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }

    private static final String EMAIL_STYLES = "<style>"
            + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
            + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
            + ".header { background: linear-gradient(135deg, #6c63ff, #4CAF50); color: #ffffff; padding: 20px; text-align: center; }"
            + ".header h1 { margin: 0; font-size: 24px; }"
            + ".content { padding: 20px; }"
            + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
            + ".cta { text-align: center; margin: 20px 0; }"
            + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
            + ".button:hover { background-color: #45a049; }"
            + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
            + ".footer a { color: #6c63ff; text-decoration: none; }"
            + "</style>";

    public static void demandSuccess(String recipientMailId, String name, String productName, int requestedQuantity, int availableQuantity) throws MessagingException {
        String subject = String.format("Product Demand Received - %s", productName);

        String htmlTextMessage = String.format(
                "<html><head>%s</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>Thank You for Your Product Request</h1></div>"
                + "<div class='content'>"
                + "<p>Hi <strong>%s</strong>,</p>"
                + "<p>Thank you for your request for <strong>%s</strong>. We have received your order for <strong>%d</strong> units.</p>"
                + "<p>We regret to inform you that only <strong>%d</strong> units are currently in stock. These units will be shipped to you immediately.</p>"
                + "<p>Rest assured, the remaining <strong>%d</strong> units are being prioritized for restocking. As soon as they become available, we will notify you and ship them without delay.</p>"
                + "<p>If you have an urgent need for the remaining units, we recommend checking back with us regularly or contacting our support team for assistance.</p>"
                + "<p>Your satisfaction is our top priority, and we are committed to ensuring a seamless experience for you.</p>"
                + "<div class='cta'><a href='https://your-app-link.com/orders' class='button'>Track Your Order</a></div>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any questions or concerns about your request, please <a href='https://your-app-link.com/support'>contact us</a>. Our support team is here to assist you.</p>"
                + "<p>Thank you for choosing <strong>Your Company Name</strong>!</p>"
                + "<p>&copy; %d Your Company Name. All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>",
                EMAIL_STYLES, name, productName, requestedQuantity, availableQuantity, requestedQuantity - availableQuantity, java.time.Year.now()
        );

        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }

    public static void orderShippedSuccess(String recipientMailId, String name, String productName, int quantity, String orderId) throws MessagingException {
        String subject = "Order Confirmed & Shipped - Order #" + orderId;

        String htmlTextMessage = "<html><head>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
                + ".header { background: linear-gradient(135deg, #6c63ff, #4CAF50); color: #ffffff; padding: 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 24px; }"
                + ".content { padding: 20px; }"
                + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
                + ".summary { background-color: #f9f9f9; padding: 10px; border-radius: 5px; margin: 15px 0; }"
                + ".summary p { margin: 5px 0; font-size: 15px; }"
                + ".cta { text-align: center; margin: 20px 0; }"
                + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
                + ".button:hover { background-color: #45a049; }"
                + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
                + ".footer a { color: #6c63ff; text-decoration: none; }"
                + "</style>"
                + "</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>Your Order is on Its Way!</h1></div>"
                + "<div class='content'>"
                + "<p>Hi <strong>" + name + "</strong>,</p>"
                + "<p>Thank you for shopping with us! We’re thrilled to let you know that your order has been successfully placed and shipped.</p>"
                + "<div class='summary'>"
                + "<p><strong>Order ID:</strong> " + orderId + "</p>"
                + "<p><strong>Product:</strong> " + productName + "</p>"
                + "<p><strong>Quantity:</strong> " + quantity + "</p>"
                + "</div>"
                + "<p>We’ve carefully packed your items and handed them over to our trusted delivery partner. You can track your shipment at any time:</p>"
                + "<div class='cta'><a href='https://your-app-link.com/track-order?orderId=" + orderId + "' class='button'>Track Your Order</a></div>"
                + "<p>If you have any concerns or need to make changes, our support team is here to assist you.</p>"
                + "<h3>Here’s what to expect next:</h3>"
                + "<ul style='padding-left: 20px;'>"
                + "<li>Your delivery will arrive at the address provided during checkout.</li>"
                + "<li>You’ll receive a notification once the package is out for delivery.</li>"
                + "<li>If you miss the delivery, our courier will attempt redelivery the next business day.</li>"
                + "</ul>"
                + "<p>We hope you enjoy your purchase! Thank you for choosing <strong>Your Company Name</strong>.</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any questions or need assistance, feel free to <a href='https://your-app-link.com/support'>contact us</a>.</p>"
                + "<p>&copy; " + java.time.Year.now() + " Your Company Name. All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }

    public static void sendContactConfirmation(String name, String email, String message) throws MessagingException {
        String subject = "Thank You for Contacting Us - Your Message Has Been Received";

        String htmlTextMessage = "<html><head>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
                + ".header { background: linear-gradient(135deg, #6c63ff, #4CAF50); color: #ffffff; padding: 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 24px; }"
                + ".content { padding: 20px; }"
                + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
                + ".cta { text-align: center; margin: 20px 0; }"
                + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
                + ".button:hover { background-color: #45a049; }"
                + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
                + ".footer a { color: #6c63ff; text-decoration: none; }"
                + "</style>"
                + "</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>We have Received Your Message!</h1></div>"
                + "<div class='content'>"
                + "<p>Hi <strong>" + name + "</strong>,</p>"
                + "<p>Thank you for reaching out to us. We have received your message and will get back to you as soon as possible. Here’s a summary of your contact request:</p>"
                + "<div class='summary'>"
                + "<p><strong>Name:</strong> " + name + "</p>"
                + "<p><strong>Email:</strong> " + email + "</p>"
                + "<p><strong>Message:</strong> " + message + "</p>"
                + "</div>"
                + "<p>If you need immediate assistance, feel free to contact us via phone or email:</p>"
                + "<ul>"
                + "<li><strong>Phone:</strong> +91 7394960743</li>"
                + "<li><strong>Email:</strong> rayeesali1510@gmail.com</li>"
                + "</ul>"
                + "<div class='cta'><a href='mailto:rayeesali1510@gmail.com' class='button'>Contact Us Again</a></div>"
                + "<p>We appreciate your patience, and someone from our team will get back to you soon.</p>"
                + "<p>Best regards,<br/>The Team at GadgetHub</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any further questions, please <a href='mailto:rayeesali1510@gmail.com'>contact us</a>.</p>"
                + "<p>&copy; " + java.time.Year.now() + " GadgetHub. All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        JavaMailUtil.sendMail(email, subject, htmlTextMessage);
        
        String htmlTextMessageForOwner = "<html><head>"
        + "<style>"
        + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
        + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
        + ".header { background: linear-gradient(135deg, #ff6c63, #ff9800); color: #ffffff; padding: 20px; text-align: center; }"
        + ".header h1 { margin: 0; font-size: 24px; }"
        + ".content { padding: 20px; }"
        + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
        + ".summary { background-color: #f9f9f9; padding: 15px; border-radius: 8px; margin-top: 10px; }"
        + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
        + "</style>"
        + "</head><body>"
        + "<div class='email-container'>"

        // Header
        + "<div class='header'><h1>New Contact Request Received</h1></div>"

        // Content
        + "<div class='content'>"
        + "<p>Hello <strong>GadgetHub Owner</strong>,</p>"
        + "<p>You have received a new message from your website contact form. Here are the details:</p>"

        + "<div class='summary'>"
        + "<p><strong>Name:</strong> " + name + "</p>"
        + "<p><strong>Email:</strong> " + email + "</p>"
        + "<p><strong>Message:</strong> " + message + "</p>"
        + "</div>"

        + "<p>Please respond to the user as soon as possible.</p>"
        + "<p>You can directly reply to this email or contact the user using the provided details.</p>"

        + "<p>Best regards,<br/>GadgetHub System</p>"
        + "</div>"

        // Footer
        + "<div class='footer'>"
        + "<p>&copy; " + java.time.Year.now() + " GadgetHub. All rights reserved.</p>"
        + "</div>"

        + "</div>"
        + "</body></html>";
        JavaMailUtil.sendMail("rayeesali1510@gmail.com", subject, htmlTextMessageForOwner);
    }

    public static void sendDemandFulfilledEmail(String recipientMailId, String name, String prodId, String productName, int requestedQuantity, int availableQuantity) throws MessagingException {
        String subject = "Exciting News! Your Desired Product is Ready for You";

        String htmlTextMessage = "<html><head>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f9f9f9; color: #333; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); }"
                + ".header { background: linear-gradient(135deg, rgba(108, 99, 255, 0.8), rgba(33, 150, 243, 0.8)), rgba(255, 255, 255, 0.2); color: #ffffff; padding: 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 26px; }"
                + ".content { padding: 20px; line-height: 1.8; font-size: 16px; }"
                + ".cta { text-align: center; margin: 20px 0; }"
                + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
                + ".button:hover { background-color: #45a049; }"
                + ".footer { background-color: #f1f1f1; color: #777; text-align: center; padding: 15px; font-size: 14px; }"
                + ".footer a { color: #2196F3; text-decoration: none; }"
                + "</style>"
                + "</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>Good News Awaits You!</h1></div>"
                + "<div class='content'>"
                + "<p>Dear <strong>" + name + "</strong>,</p>"
                + "<p>We are thrilled to inform you that the product you’ve been eagerly waiting for, <strong>" + productName + "</strong>, is now back in stock! This product is in high demand, and we’ve set aside just enough to fulfill your needs.</p>"
                + "<p>Here’s the summary of your request:</p>"
                + "<ul>"
                + "<li><strong>Product ID:</strong> " + prodId + "</li>"
                + "<li><strong>Requested Quantity:</strong> " + requestedQuantity + "</li>"
                + "<li><strong>Available Quantity:</strong> " + availableQuantity + "</li>"
                + "</ul>"
                + "<p>To ensure you don’t miss out, we recommend you act quickly and confirm your purchase while supplies last. Simply click the button below to visit our website and place your order.</p>"
                + "<div class='cta'><a href='https://yourwebsite.com/product/" + prodId + "' class='button'>Buy Now</a></div>"
                + "<p>If you need more assistance or have specific questions, our team is here to help. Reach out to us anytime at the contact details provided below.</p>"
                + "<p>We truly value your trust and support. Thank you for choosing us, and we look forward to serving you with the best shopping experience.</p>"
                + "<p>Warm regards,<br/>The Team at GadgetHub</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any questions, please <a href='mailto:rayeesali1510@gmail.com'>contact us</a>.</p>"
                + "<p>&copy; " + java.time.Year.now() + " GadgetHub. All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }

    public static void orderPlacedSuccess(String recipientMailId, String name, int quantity, String orderId) throws MessagingException {
        String subject = "Order Confirmed - Order #" + orderId;

        String htmlTextMessage = "<html><head>"
                + "<style>"
                + "body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; padding: 0; background-color: #f3f3f3; color: #333; }"
                + ".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1); }"
                + ".header { background: linear-gradient(135deg, #6c63ff, #4CAF50); color: #ffffff; padding: 20px; text-align: center; }"
                + ".header h1 { margin: 0; font-size: 24px; }"
                + ".content { padding: 20px; }"
                + ".content p { line-height: 1.6; margin: 10px 0; font-size: 16px; }"
                + ".summary { background-color: #f9f9f9; padding: 10px; border-radius: 5px; margin: 15px 0; }"
                + ".summary p { margin: 5px 0; font-size: 15px; }"
                + ".cta { text-align: center; margin: 20px 0; }"
                + ".button { display: inline-block; padding: 12px 20px; background-color: #4CAF50; color: #fff; text-decoration: none; font-size: 16px; border-radius: 5px; transition: background-color 0.3s ease; }"
                + ".button:hover { background-color: #45a049; }"
                + ".footer { background-color: #f3f3f3; color: #888; text-align: center; padding: 15px; font-size: 14px; }"
                + ".footer a { color: #6c63ff; text-decoration: none; }"
                + "</style>"
                + "</head><body>"
                + "<div class='email-container'>"
                + "<div class='header'><h1>Your Order Has Been Successfully Placed!</h1></div>"
                + "<div class='content'>"
                + "<p>Hi <strong>" + name + "</strong>,</p>"
                + "<p>Thank you for placing your order with us! We’re excited to process your order and ensure it reaches you on time.</p>"
                + "<div class='summary'>"
                + "<p><strong>Order ID:</strong> " + orderId + "</p>"
                + "<p><strong>Quantity:</strong> " + quantity + "</p>"
                + "</div>"
                + "<p>We’re preparing your order for shipment and will notify you once it’s on its way.</p>"
                + "<div class='cta'><a href='https://your-app-link.com/order-details?orderId=" + orderId + "' class='button'>View Order Details</a></div>"
                + "<p>If you have any questions or need assistance, feel free to reach out to our support team.</p>"
                + "<h3>What happens next:</h3>"
                + "<ul style='padding-left: 20px;'>"
                + "<li>Your order will be processed within 1-2 business days.</li>"
                + "<li>You’ll receive an update when your order has been shipped.</li>"
                + "<li>You can track your order anytime using the link above.</li>"
                + "</ul>"
                + "<p>Thank you for choosing <strong>Your Company Name</strong>. We hope you enjoy your shopping experience!</p>"
                + "</div>"
                + "<div class='footer'>"
                + "<p>If you have any questions or need assistance, feel free to <a href='https://your-app-link.com/support'>contact us</a>.</p>"
                + "<p>&copy; " + java.time.Year.now() + " Your Company Name. All rights reserved.</p>"
                + "</div>"
                + "</div>"
                + "</body></html>";

        JavaMailUtil.sendMail(recipientMailId, subject, htmlTextMessage);
    }

}

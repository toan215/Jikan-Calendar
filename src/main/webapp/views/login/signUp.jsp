<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Sign Up</title>
    <!-- Tailwind CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
  </head>
  <body class="min-h-screen flex items-center justify-center bg-white">
    <div class="w-full h-screen flex">
      <!-- Left: Background Image with Quote -->
      <div
        class="hidden md:flex w-1/2 h-full relative items-center justify-center bg-[url('<%=request.getContextPath()%>/assets/flower-bg.jpg')] bg-cover bg-center"
      >
        <div class="absolute inset-0 bg-white bg-opacity-20"></div>
        <div class="relative z-10 w-4/5 mx-auto text-center">
          <span
            class="text-6xl text-gray-200 absolute -mt-8 -ml-8 select-none left-0 top-0"
            >“</span
          >
          <p class="text-2xl font-light text-gray-700 mt-4">
            Time is the
            <span class="font-bold text-blue-500">most valuable</span> thing a
            man can <span class="font-bold text-blue-500">spend</span>.
          </p>
          <p class="mt-6 text-right text-gray-600 font-medium">
            - Theophrastus
          </p>
        </div>
      </div>

      <!-- Right: Sign Up Form -->
      <div
        class="w-full md:w-1/2 flex flex-col justify-center items-center px-8 bg-white"
      >
        <div class="w-full max-w-md">
          <h2 class="text-3xl font-bold mb-8">Sign Up</h2>
          <form
            name="signup"
            method="post"
            action="<%=request.getContextPath()%>/signup"
          >
            <!-- Username -->
            <div class="mb-4">
              <div
                class="flex items-center border border-gray-300 rounded-lg px-3 py-2 bg-white focus-within:ring-2 focus-within:ring-blue-400 gap-2"
              >
                <!-- User Icon -->
                <img
                  src="<%=request.getContextPath()%>/assets/User.svg"
                  alt="User"
                  class="w-5 h-5"
                />
                <input
                  type="text"
                  name="username"
                  id="username"
                  placeholder="Your UserName"
                  class="w-full outline-none bg-transparent"
                  required
                />
              </div>
            </div>
            <!-- Email -->
            <div class="mb-4">
              <div
                class="flex items-center border border-gray-300 rounded-lg px-3 py-2 bg-white focus-within:ring-2 focus-within:ring-blue-400 gap-2"
              >
                <!-- Email Icon -->
                <img
                  src="<%=request.getContextPath()%>/assets/EmailLog.svg"
                  alt="Email"
                  class="w-5 h-5"
                />
                <input
                  type="email"
                  name="email"
                  id="email"
                  placeholder="Your email"
                  class="w-full outline-none bg-transparent"
                  required
                />
              </div>
            </div>
            <!-- Password -->
            <div class="mb-4">
              <div
                class="flex items-center border border-gray-300 rounded-lg px-3 py-2 bg-white focus-within:ring-2 focus-within:ring-blue-400 gap-2"
              >
                <!-- Lock Icon -->
                <img
                  src="<%=request.getContextPath()%>/assets/Key.svg"
                  alt="Password"
                  class="w-5 h-5"
                />
                <input
                  type="password"
                  name="password"
                  id="password"
                  placeholder="Password"
                  class="w-full outline-none bg-transparent"
                  required
                  minlength="8"
                />
              </div>
              <span class="text-xs text-gray-400 ml-1"
                >Must be at least 8 characters.</span
              >
            </div>
            <!-- Repeat Password -->
            <div class="mb-4">
              <div
                class="flex items-center border border-gray-300 rounded-lg px-3 py-2 bg-white focus-within:ring-2 focus-within:ring-blue-400 gap-2"
              >
                <!-- Lock Icon -->
                <img
                  src="<%=request.getContextPath()%>/assets/Key.svg"
                  alt="Password"
                  class="w-5 h-5"
                />
                <input
                  type="password"
                  name="repass"
                  id="repass"
                  placeholder="Repeat Password"
                  class="w-full outline-none bg-transparent"
                  required
                />
              </div>
            </div>
            <button
              type="submit"
              class="w-full bg-blue-500 text-white py-3 rounded-lg font-semibold hover:bg-blue-600 transition mb-4"
            >
              Sign Up
            </button>
          </form>
          <div class="flex items-center my-4">
            <div class="flex-grow h-px bg-gray-200"></div>
            <span class="mx-2 text-gray-400">or</span>
            <div class="flex-grow h-px bg-gray-200"></div>
          </div>
          <% // Dynamically construct the redirect URI based on current
          environment String scheme = request.getScheme(); // http or https
          String serverName = request.getServerName(); // localhost or
          jikan-calendar.onrender.com int serverPort = request.getServerPort();
          String contextPath = request.getContextPath(); //
          /PRJ_Assignment_toidaiii or /calendar String redirectUri; if
          (serverPort == 80 || serverPort == 443 || serverPort == 10000) { //
          Production (Render uses port 10000 internally, but external is 80/443)
          redirectUri = scheme + "://" + serverName + contextPath + "/login"; }
          else { // Local development redirectUri = scheme + "://" + serverName
          + ":" + serverPort + contextPath + "/login"; } String googleClientId =
          "1097365484665-9av0b9o7gjpq7j5co0ecf8dbipsbpvkd.apps.googleusercontent.com";
          String googleAuthUrl =
          "https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid"
          + "&redirect_uri=" + java.net.URLEncoder.encode(redirectUri, "UTF-8")
          + "&response_type=code" + "&client_id=" + googleClientId +
          "&prompt=consent"; %>
          <a
            href="<%= googleAuthUrl %>"
            class="w-full flex items-center justify-center border py-3 rounded-lg hover:bg-gray-100 transition mb-6"
          >
            <!--Icon logo Google-->
            <img
              src="<%=request.getContextPath()%>/assets/Google.svg"
              alt="Google"
              class="w-5 h-5 mr-2"
            />
            Google
          </a>
          <div class="text-center text-gray-500 text-sm">
            Already have an account?
            <a href="login" class="text-blue-500 font-semibold hover:underline"
              >Log In</a
            >
          </div>
        </div>
      </div>
    </div>
  </body>
</html>

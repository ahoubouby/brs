# # Extending image
# FROM node:carbon

# RUN apt-get update
# RUN apt-get upgrade -y
# RUN apt-get -y install autoconf automake libtool nasm make pkg-config git apt-utils

# # Create app directory
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app

# # Versions
# RUN npm -v
# RUN node -v

# # Install app dependencies
# COPY client-reactjs/package.json /usr/src/app/

# RUN npm install

# # Bundle app source
# COPY client-reactjs /usr/src/app

# # Port to listener
# EXPOSE 3000

# # Environment variables
# ENV NODE_ENV production
# ENV PORT 3000
# ENV PUBLIC_PATH "/"

# RUN npm run start:build

# Main command
CMD [ "npm", "run", "start:server" ]

FROM node:12-alpine as build
WORKDIR /app
# Install app dependencies
COPY client-reactjs/package.json  app/
RUN npm install --only=prod
COPY client-reactjs/package.json /usr/src/app/
RUN npm run build
FROM nginx:1.16.0-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

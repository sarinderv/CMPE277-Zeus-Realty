const env = process.env;

const config = {
  db: { /* do not put password or any sensitive info here, done only for demo */
    host: env.DB_HOST || 'heffalump.db.elephantsql.com',
    port: env.DB_PORT || '5432',
    user: env.DB_USER || 'fhdmjxva',
    password: env.DB_PASSWORD || 'w3vlGlT7zdtW25mvdiCduSHsYhbv2ij9',
    database: env.DB_NAME || 'fhdmjxva',
  },
  mongodb: { /* do not put password or any sensitive info here, done only for demo */
    DB_URI: env.MONGO_URI_DB_NAME ||  'mongodb+srv://cmpe277:Qwerty123@cluster0.yfpgd.mongodb.net/myFirstDatabase?retryWrites=true&w=majority'
  },
  listPerPage: env.LIST_PER_PAGE || 10,
};

module.exports = config;

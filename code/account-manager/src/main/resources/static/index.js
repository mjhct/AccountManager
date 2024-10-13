const { createApp } = Vue
const { createVuetify } = Vuetify

// 同步请求后端接口获取数据的函数，命名方式：api + xxxx
async function apiQueryList(pageNumber, pageSize, decryptFlag) {
  try {
    let response = await axios.post('/accountmanager/account/query', 
                                {
                                  page_number: pageNumber,
                                  page_size: pageSize,
                                  decrypt: decryptFlag,
                                  fuzzy_name: null
                                });
    let resJson = response.data;
    console.log("111");
    console.log(resJson);
    return resJson;
  } catch (error) {
    console.error(error);
  }
}

const vuetify = createVuetify()

const FakeAPI = {
    async fetch ({ page, itemsPerPage, sortBy }) {
      return new Promise(resolve => {
        setTimeout(() => {
          axios.post('/accountmanager/account/query', {
            page_number: page,
            page_size: 10,
            decrypt: true,
            fuzzy_name: null
        })
        .then(function (response) {
            let resJson = response.data;
            console.log(resJson);
            resolve({ items: resJson.data.list, total: resJson.data.total_records })
        })
        .catch(function (error) {
        })
        .finally(function () {
        });

        }, 500)
      })
    },
  }

const App = {

    data() {
        return {

            // 搜索框
            

            // 分页
            pageNumber: 1,
            pageSize: 10,
            totalRecords: 0,
            
            // 列表表头、内容
            headers: [
              { key: 'name', title: '名称', sortable: false},
              { key: 'url', title: '网址', sortable: false},
              { key: 'username', title: '账号', sortable: false},
              { key: 'password', title: '密码', sortable: false},
              { key: 'remark', title: '备注', sortable: false},
              { key: 'create_time', title: '创建时间', sortable: false},
              { key: 'update_time', title: '更新时间', sortable: false},
            ],
            serverDatas: [],
            
            loading: false,
            randomPassword: null,
            decryptFlag: true,
        }
    },

    methods: {

        // 随机密码
        apiGetRandomPassword() {
            axios.get('/accountmanager/password/get')
                .then(function (response) {
                    let resJson = response.data;
                    randomPassword = resJson.data;
                    console.log(randomPassword);
                })
                .catch(function (error) {
                })
                .finally(function () {
                });
        },

        // 根据id查询

        // 列表查询
        loadItems ({ page, pageSize, sortBy }) {
            this.loading = true;
            FakeAPI.fetch({ page, pageSize, sortBy }).then(({ items, total }) => {
                console.log(items);
              this.serverDatas = items;
              this.totalRecords = total;
              this.loading = false;
            })
          },

        async test() {
          let resJson = await apiQueryList(1, 10, true);
          console.log("222");
          console.log(resJson);
        }
        // apiQueryList() {
        //     this.loading = true;
        //     axios.post('/accountmanager/account/query', {
        //         page_number: this.pageNumber,
        //         page_size: this.pageSize,
        //         decrypt: this.decryptFlag,
        //         fuzzy_name: null
        //     })
        //     .then(function (response) {
        //         let resJson = response.data;
        //         console.log(resJson);
        //         this.loading = false;
        //         this.serverDatas = [];
        //         this.totalRecords = resJson.data.total_records;
                
        //     })
        //     .catch(function (error) {
        //     })
        //     .finally(function () {
        //     });
        // },

        // 新增

        // 修改

        // 删除

        // 导入
        
        // 导出

    },

}

const app = createApp(App)
app.use(vuetify).mount('#app')
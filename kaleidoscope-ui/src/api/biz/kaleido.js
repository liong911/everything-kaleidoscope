import request from '@/utils/request'

// 查询导航列表
export function getKaleidoList() {
  return request({
    url: '/kaleido/getList',
    headers: {
      isToken: false
    },
    method: 'get',
    params: null
  })
}
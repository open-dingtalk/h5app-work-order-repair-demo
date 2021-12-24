import React from 'react'
import { Button } from 'antd-mobile'
import { useNavigate } from 'react-router-dom'
import './index.css'

const Main = () => {
  let navigate = useNavigate()

  const goTo = (path) => {
    console.log('---path----', path)
    navigate(path)
  }
  return (
    <div className="main">
      <div className={'title'}>操作列表</div>
      <Button
        className={'button'}
        block
        color="primary"
        onClick={() => {
          goTo('/edit')
        }}
      >
        配置工单
      </Button>
      <Button
        className={'button'}
        block
        color="primary"
        onClick={() => {
          goTo('/distribute')
        }}
      >
        填写工单
      </Button>
    </div>
  )
}
export default Main

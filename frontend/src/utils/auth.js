import Cookies from 'js-cookie'

const TokenKey = 'accessToken'

const RefreshToken = 'refreshToken'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function getRefreshToken() {
  return Cookies.get(RefreshToken)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function setRefreshToken(refreshToken) {
  return Cookies.set(RefreshToken, refreshToken)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function removeRefreshToken() {
  return Cookies.remove(RefreshToken)
}